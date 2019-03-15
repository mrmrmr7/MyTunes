package com.mrmrmr7.mytunes.service.impl;

import com.mrmrmr7.mytunes.dao.GenericDao;
import com.mrmrmr7.mytunes.dao.Identified;
import com.mrmrmr7.mytunes.dao.TransactionManager;
import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.dao.impl.AuthorDao;
import com.mrmrmr7.mytunes.dao.impl.JdbcDaoFactory;
import com.mrmrmr7.mytunes.dao.impl.TransactionManagerImpl;
import com.mrmrmr7.mytunes.dto.CompositionDto;
import com.mrmrmr7.mytunes.entity.Album;
import com.mrmrmr7.mytunes.entity.Author;
import com.mrmrmr7.mytunes.entity.Composition;
import com.mrmrmr7.mytunes.entity.UserComposition;
import com.mrmrmr7.mytunes.service.ServiceException;
import com.mrmrmr7.mytunes.service.CompositionDtoService;
import com.mrmrmr7.mytunes.util.ProtectionUtil;
import io.jsonwebtoken.Claims;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CompositionDtoServiceImpl implements CompositionDtoService {

    @Override
    public List<CompositionDto> getAllCompositionDto() throws ServiceException {
        List<CompositionDto> compositionDtoList = new ArrayList<>();

        TransactionManager transactionManager = new TransactionManagerImpl();
        try {
            GenericDao compositionDao = JdbcDaoFactory.getInstance().getTransactionalDao(Composition.class);
            GenericDao albumDao = JdbcDaoFactory.getInstance().getTransactionalDao(Album.class);
            GenericDao authorDao = JdbcDaoFactory.getInstance().getTransactionalDao(Author.class);

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
            } catch (DaoException e1) {
                throw new ServiceException(e.getMessage());
            }
            throw new ServiceException(e.getMessage());
        } finally {
            try {
                transactionManager.end();
            } catch (DaoException e) {
                throw new ServiceException(e.getMessage());
            }
        }

        return compositionDtoList;
    }

    @Override
    public List<CompositionDto> getAllNotUserCompositionDto(HttpServletRequest request) throws ServiceException { //FIXME bad name
        List<CompositionDto> compositionDtoList = new ArrayList<>();
        Optional<UserComposition> userCompositionOptional = null;

        Cookie[] cookieArray = request.getCookies();
        Claims claims = ProtectionUtil.getClaimsFromCookies(cookieArray);

        TransactionManager transactionManager = new TransactionManagerImpl();
        try {
            GenericDao compositionDao = JdbcDaoFactory.getInstance().getTransactionalDao(Composition.class);
            GenericDao albumDao = JdbcDaoFactory.getInstance().getTransactionalDao(Album.class);
            GenericDao authorDao = JdbcDaoFactory.getInstance().getTransactionalDao(Author.class);
            GenericDao userCompositionDao = JdbcDaoFactory.getInstance().getTransactionalDao(UserComposition.class);

            transactionManager = new TransactionManagerImpl();
            transactionManager.begin(compositionDao, albumDao, authorDao, userCompositionDao);

            List<Composition> compositionList = compositionDao.getAll();

            userCompositionOptional = userCompositionDao.getByPK(claims.get("userId", Integer.class));

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
                    e.printStackTrace();
                }

                compositionDtoList.add(compositionDto);
            }

            transactionManager.commit();
        } catch (DaoException e) {
            try {
                transactionManager.rollBack();
            } catch (DaoException e1) {
                throw new ServiceException(e.getMessage());
            }
            throw new ServiceException(e.getMessage());
        } finally {
            try {
                transactionManager.end();
            } catch (DaoException e) {
                throw new ServiceException(e.getMessage());
            }
        }

        return compositionDtoList;
    }
}
