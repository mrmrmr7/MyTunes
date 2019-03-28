package com.mrmrmr7.mytunes.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mrmrmr7.mytunes.controller.command.RequestDirector;
import com.mrmrmr7.mytunes.dao.CompositionDaoExtended;
import com.mrmrmr7.mytunes.dao.GenericDao;
import com.mrmrmr7.mytunes.dao.TransactionManager;
import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.dao.impl.JdbcDaoFactory;
import com.mrmrmr7.mytunes.dao.impl.TransactionManagerImpl;
import com.mrmrmr7.mytunes.dto.CompositionDto;
import com.mrmrmr7.mytunes.entity.*;
import com.mrmrmr7.mytunes.service.CompositionService;
import com.mrmrmr7.mytunes.service.exception.ServiceException;
import com.mrmrmr7.mytunes.util.ExceptionDirector;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CompositionServiceImpl implements CompositionService {

    private static final String PARAMETER_COMPOSITION_NAME = "compositionName";

    @Override
    public List<CompositionDto> getAllCompositionDto() throws ServiceException {
        List<CompositionDto> compositionDtoList = new ArrayList<>();

        TransactionManager transactionManager = new TransactionManagerImpl();
        try {
            GenericDao<Composition, Integer> compositionDao = JdbcDaoFactory.getInstance().getTransactionalDao(Composition.class);
            GenericDao<Album, Integer> albumDao = JdbcDaoFactory.getInstance().getTransactionalDao(Album.class);
            GenericDao<Author, Integer> authorDao = JdbcDaoFactory.getInstance().getTransactionalDao(Author.class);

            transactionManager = new TransactionManagerImpl();
            transactionManager.begin(compositionDao, albumDao, authorDao);

            List<Composition> compositionList = compositionDao.getAll();

            for (Composition c : compositionList) {
                CompositionDto compositionDto = new CompositionDto();
                compositionDto.setName(c.getName());
                compositionDto.setPrice(c.getPrice());

                try {
                    Optional<Album> albumOptional = albumDao.getByPK(c.getAlbum_id());
                    if (albumOptional.isPresent()) {
                        Album a = albumOptional.get();
                        compositionDto.setAlbum(a.getName());
                        Optional<Author> authorOptional = authorDao.getByPK(a.getAuthor_id());
                        authorOptional.ifPresent(auth -> compositionDto.setAuthor(auth.getPseudonim()));
                    }
                } catch (DaoException e) {
                    e.printStackTrace();
                }

                compositionDtoList.add(compositionDto);
            }

            transactionManager.commit();
        } catch (DaoException e) {
            try {
                transactionManager.rollBack();
                throw new ServiceException(MessageFormat.format(ExceptionDirector.EXC_MSG, ExceptionDirector.IMPOSSIBLE_GET) + e.getMessage());
            } catch (DaoException e1) {
                throw new ServiceException(MessageFormat.format(ExceptionDirector.EXC_MSG, ExceptionDirector.IMPOSSIBLE_ROLL_BACK) + e.getMessage());
            }
        } finally {
            try {
                transactionManager.end();
            } catch (DaoException e) {
                throw new ServiceException(MessageFormat.format(ExceptionDirector.EXC_MSG, ExceptionDirector.IMPOSSIBLE_END_TRANSACTION) + e.getMessage());
            }
        }

        return compositionDtoList;
    }

    @Override
    public List<CompositionDto> getAllNotUserCompositionDto(HttpServletRequest request) throws ServiceException { //FIXME bad name
        List<CompositionDto> compositionDtoList = new ArrayList<>();
        Optional<UserComposition> userCompositionOptional = null;

        Cookie[] cookies = request.getCookies();
        Optional<Cookie> cookieToken = Arrays.stream(cookies).filter(s -> s.getName().equals("token")).findFirst();
        DecodedJWT decodedJWT = JWT.decode(cookieToken.get().getValue());

        TransactionManager transactionManager = new TransactionManagerImpl();
        try {
            GenericDao<Composition, Integer> compositionDao = JdbcDaoFactory.getInstance().getTransactionalDao(Composition.class);
            GenericDao<Album, Integer> albumDao = JdbcDaoFactory.getInstance().getTransactionalDao(Album.class);
            GenericDao<Author, Integer> authorDao = JdbcDaoFactory.getInstance().getTransactionalDao(Author.class);
            GenericDao<UserComposition, Integer> userCompositionDao = JdbcDaoFactory.getInstance().getTransactionalDao(UserComposition.class);

            transactionManager = new TransactionManagerImpl();
            transactionManager.begin(compositionDao, albumDao, authorDao, userCompositionDao);

            List<Composition> compositionList = compositionDao.getAll();

            userCompositionOptional = userCompositionDao.getByPK(decodedJWT.getClaim("userId").asInt());

            List<Composition> rightCompositionList = new ArrayList<>();

            userCompositionOptional.ifPresent(
                    u -> {
                        for (Composition c : compositionList) {
                            if (!u.getCompositionIdList().contains(c.getId())) {
                                rightCompositionList.add(c);
                            }
                        }
                    }
            );


            for (Composition c : rightCompositionList) {
                CompositionDto compositionDto = new CompositionDto();
                compositionDto.setName(c.getName());
                compositionDto.setPrice(c.getPrice());

                try {
                    Optional<Album> albumOptional = albumDao.getByPK(c.getAlbum_id());
                    if (albumOptional.isPresent()) {
                        Album a = albumOptional.get();
                        compositionDto.setAlbum(a.getName());
                        Optional<Author> authorOptional = authorDao.getByPK(a.getAuthor_id());
                        authorOptional.ifPresent(auth -> compositionDto.setAuthor(auth.getPseudonim()));
                    }
                } catch (DaoException e) {
                    throw new ServiceException(MessageFormat.format(ExceptionDirector.EXC_MSG, ExceptionDirector.IMPOSSIBLE_GET_DATA) + e.getMessage());
                }

                compositionDtoList.add(compositionDto);
            }

            transactionManager.commit();
        } catch (DaoException e) {
            try {
                transactionManager.rollBack();
                throw new ServiceException(MessageFormat.format(ExceptionDirector.EXC_MSG, ExceptionDirector.IMPOSSIBLE_GET) + e.getMessage());
            } catch (DaoException e1) {
                throw new ServiceException(MessageFormat.format(ExceptionDirector.EXC_MSG, ExceptionDirector.IMPOSSIBLE_ROLL_BACK) + e.getMessage());
            }
        } finally {
            try {
                transactionManager.end();
            } catch (DaoException e) {
                throw new ServiceException(MessageFormat.format(ExceptionDirector.EXC_MSG, ExceptionDirector.IMPOSSIBLE_END_TRANSACTION) + e.getMessage());
            }
        }

        return compositionDtoList;
    }

    @Override
    public List<CompositionDto> getAllUserCompositionDto(HttpServletRequest request) throws ServiceException {
        Cookie[] cookies = request.getCookies();
        Optional<Cookie> cookieToken = Arrays.stream(cookies).filter(s -> s.getName().equals(RequestDirector.TOKEN.getValue())).findFirst();
        DecodedJWT decodedJWT = JWT.decode(cookieToken.get().getValue());

        TransactionManager transactionManager = new TransactionManagerImpl();

        List<CompositionDto> compositionDtoList = new ArrayList<>();

        try {
            GenericDao<UserComposition, Integer> userCompositionDao = JdbcDaoFactory.getInstance().getTransactionalDao(UserComposition.class);
            GenericDao<Composition, Integer> compositionDao = JdbcDaoFactory.getInstance().getTransactionalDao(Composition.class);
            GenericDao<Album, Integer> albumDao = JdbcDaoFactory.getInstance().getTransactionalDao(Album.class);
            GenericDao<Author, Integer> authorDao = JdbcDaoFactory.getInstance().getTransactionalDao(Author.class);

            transactionManager.begin(userCompositionDao, compositionDao, albumDao, authorDao);

            Optional<UserComposition> userCompositionOptional = userCompositionDao.getByPK(decodedJWT.getClaim("userId").asInt());
            if (userCompositionOptional.isPresent()) {
                List<Integer> userCompositionIdList = userCompositionOptional.get().getCompositionIdList();

                for (Integer each : userCompositionIdList) {

                    Optional<Composition> compositionOptional = compositionDao.getByPK(each);
                    if (compositionOptional.isPresent()) {

                        Optional<Album> albumOptional = albumDao.getByPK(compositionOptional.get().getAlbum_id());
                        if (albumOptional.isPresent()) {

                            Optional<Author> authorOptional = authorDao.getByPK(albumOptional.get().getAuthor_id());
                            if(authorOptional.isPresent()) {

                                CompositionDto compositionDto = new CompositionDto();
                                compositionDto.setAlbum(albumOptional.get().getName());
                                compositionDto.setAuthor(authorOptional.get().getPseudonim());
                                compositionDto.setName(compositionOptional.get().getName());

                                compositionDtoList.add(compositionDto);
                            }
                        }
                    }
                }
            }

            transactionManager.commit();
        } catch (DaoException e) {
            try {
                transactionManager.rollBack();
                throw new ServiceException(MessageFormat.format(ExceptionDirector.EXC_MSG, ExceptionDirector.IMPOSSIBLE_GET) + e.getMessage());
            } catch (DaoException e1) {
                throw new ServiceException(MessageFormat.format(ExceptionDirector.EXC_MSG, ExceptionDirector.IMPOSSIBLE_ROLL_BACK) + e.getMessage());
            }
        } finally {
            try {
                transactionManager.end();
            } catch (DaoException e) {
                throw new ServiceException(MessageFormat.format(ExceptionDirector.EXC_MSG, ExceptionDirector.IMPOSSIBLE_END_TRANSACTION) + e.getMessage());
            }
        }

        return compositionDtoList;
    }

    @Override
    public Optional<Composition> getCompositionDtoByName(String name) throws ServiceException {
        Optional<Composition> compositionOptional;

        try {
            compositionOptional =((CompositionDaoExtended)JdbcDaoFactory.getInstance().getDao(Composition.class)).getByName(name);
        } catch (DaoException e) {
            throw new ServiceException(MessageFormat.format(ExceptionDirector.EXC_MSG, ExceptionDirector.IMPOSSIBLE_GET_DATA) + e.getMessage());
        }

        return compositionOptional;
    }

    @Override
    public boolean addComposition(HttpServletRequest request) throws ServiceException {
        String compositionName = request.getParameter(PARAMETER_COMPOSITION_NAME);
        int compositionPrice = Integer.parseInt(request.getParameter("compositionPrice"));
        int compositionYear = Integer.parseInt(request.getParameter("compositionYear"));
        int albumId = Integer.parseInt(request.getParameter("albumId"));
        int authorId = Integer.parseInt(request.getParameter("authorId"));

        TransactionManager transactionManager = new TransactionManagerImpl();
        try {
            GenericDao<Album, Integer> albumDao = JdbcDaoFactory.getInstance().getTransactionalDao(Album.class);
            GenericDao<Composition, Integer> compositionDao = JdbcDaoFactory.getInstance().getTransactionalDao(Composition.class);

            transactionManager.begin(albumDao, compositionDao);

            Optional<Album> albumOptional = albumDao.getByPK(albumId);

            if (albumOptional.isPresent()) {
                if (albumOptional.get().getAuthor_id() == authorId) {
                    Composition composition = new Composition();
                    composition.setAlbum_id(albumId);
                    composition.setPrice(compositionPrice);
                    composition.setName(compositionName);
                    composition.setYear(compositionYear);
                    compositionDao.insert(composition);
                } else {
                    return false;
                }
            } else {
                return false;
            }

            transactionManager.commit();
        } catch (DaoException e) {
            try {
                transactionManager.rollBack();
                throw new ServiceException(MessageFormat.format(ExceptionDirector.EXC_MSG, ExceptionDirector.IMPOSSIBLE_GET) + e.getMessage());
            } catch (DaoException e1) {
                throw new ServiceException(MessageFormat.format(ExceptionDirector.EXC_MSG, ExceptionDirector.IMPOSSIBLE_ROLL_BACK ) + e.getMessage());
            }
        } finally {
            try {
                transactionManager.end();
            } catch (DaoException e) {
                throw new ServiceException(MessageFormat.format(ExceptionDirector.EXC_MSG, ExceptionDirector.IMPOSSIBLE_END_TRANSACTION) + e.getMessage());
            }
        }

        return true;
    }

    @Override
    public boolean updateComposition(HttpServletRequest request) throws ServiceException {
        String compositionName = request.getParameter(PARAMETER_COMPOSITION_NAME);
        int compositionPrice = Integer.parseInt(request.getParameter("compositionPrice"));
        int compositionYear = Integer.parseInt(request.getParameter("compositionYear"));
        int albumId = Integer.parseInt(request.getParameter("albumId"));
        int compositionId = Integer.parseInt(request.getParameter("compositionId"));

        Composition composition = new Composition();

        composition.setYear(compositionYear);
        composition.setName(compositionName);
        composition.setPrice(compositionPrice);
        composition.setAlbum_id(albumId);
        composition.setid(compositionId);

        try {
            JdbcDaoFactory.getInstance().getDao(Composition.class).update(composition);
        } catch (DaoException e) {
            throw new ServiceException(MessageFormat.format(ExceptionDirector.EXC_MSG, ExceptionDirector.IMPOSSIBLE_GET) + e.getMessage());
        }

        return true;
    }
}
