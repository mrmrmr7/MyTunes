package com.mrmrmr7.mytunes.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mrmrmr7.mytunes.dao.GenericDao;
import com.mrmrmr7.mytunes.dao.TransactionManager;
import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.dao.impl.JdbcDaoFactory;
import com.mrmrmr7.mytunes.dao.impl.TransactionManagerImpl;
import com.mrmrmr7.mytunes.dto.AlbumDto;
import com.mrmrmr7.mytunes.entity.*;
import com.mrmrmr7.mytunes.service.AlbumService;
import com.mrmrmr7.mytunes.service.exception.ServiceException;
import com.mrmrmr7.mytunes.util.ExceptionDirector;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class AlbumServiceImpl implements AlbumService {

    @Override
    public List<Album> getAllAlbum() throws ServiceException {
        List<Album> albumList = new ArrayList<>();
        try {
            albumList = JdbcDaoFactory.getInstance().getDao(Album.class).getAll();
        } catch (DaoException e) {
            throw new ServiceException(MessageFormat.format(ExceptionDirector.EXC_MSG, ExceptionDirector.IMPOSSIBLE_GET_DAO));
        }
        return albumList;
    }

    @Override
    public List<AlbumDto> getAllAlbumDto() throws ServiceException {
        List<AlbumDto> albumDtoList = new ArrayList<>();

        TransactionManager transactionManager = new TransactionManagerImpl();
        try {
            GenericDao albumDao = JdbcDaoFactory.getInstance().getTransactionalDao(Album.class);
            GenericDao genreDao = JdbcDaoFactory.getInstance().getTransactionalDao(Genre.class);
            GenericDao authorDao = JdbcDaoFactory.getInstance().getTransactionalDao(Author.class);

            transactionManager = new TransactionManagerImpl();
            transactionManager.begin(albumDao, genreDao, authorDao);

            List<Album> albumList = albumDao.getAll();

            for (Album c : albumList) {
                AlbumDto albumDto = new AlbumDto();
                albumDto.setName(c.getName());
                albumDto.setPrice(c.getPrice());

                try {
                    Optional<Author> authorOptional = authorDao.getByPK(c.getAuthor_id());
                    if (authorOptional.isPresent()) {
                        Author a = authorOptional.get();
                        albumDto.setAuthor(a.getPseudonim());
                    }

                    Optional<Genre> genreOptional = genreDao.getByPK(c.getGenre_id());
                    if (genreOptional.isPresent()) {
                        Genre genre = genreOptional.get();
                        albumDto.setGenre(genre.getGenre());
                    }
                } catch (DaoException e) {
                    throw new ServiceException(MessageFormat.format(ExceptionDirector.EXC_MSG, ExceptionDirector.IMPOSSIBLE_GET_DATA) + e.getMessage());
                }

                albumDtoList.add(albumDto);
            }

            transactionManager.commit();
        } catch (DaoException e) {
            try {
                transactionManager.rollBack();
                throw new ServiceException(MessageFormat.format(ExceptionDirector.EXC_MSG, ExceptionDirector.IMPOSSIBLE_GET) + e.getMessage());
            } catch (DaoException e1) {
                throw new ServiceException(MessageFormat.format(ExceptionDirector.EXC_MSG, ExceptionDirector.IMPOSSIBLE_ROLL_BACK) + e1.getMessage());
            }
        } finally {
            try {
                transactionManager.end();
            } catch (DaoException e) {
                throw new ServiceException(MessageFormat.format(ExceptionDirector.EXC_MSG, ExceptionDirector.IMPOSSIBLE_END_TRANSACTION) + e.getMessage());
            }
        }

        return albumDtoList;
    }

    @Override
    public List<AlbumDto> getAllNotUserAlbumDto(HttpServletRequest request) throws ServiceException { //FIXME bad name
        List<AlbumDto> albumDtoList = new ArrayList<>();
        Optional<UserAlbum> userAlbumOptional = null;

        Cookie[] cookies = request.getCookies();
        Optional<Cookie> cookieToken = Arrays.stream(cookies).filter(s -> s.getName().equals("token")).findFirst();
        DecodedJWT decodedJWT = JWT.decode(cookieToken.get().getValue());

        TransactionManager transactionManager = new TransactionManagerImpl();
        try {
            GenericDao<Album, Integer> albumDao = JdbcDaoFactory.getInstance().getTransactionalDao(Album.class);
            GenericDao<Genre, Integer> genreDao = JdbcDaoFactory.getInstance().getTransactionalDao(Genre.class);
            GenericDao<Author, Integer> authorDao = JdbcDaoFactory.getInstance().getTransactionalDao(Author.class);
            GenericDao<UserAlbum, Integer> userAlbumDao = JdbcDaoFactory.getInstance().getTransactionalDao(UserAlbum.class);

            transactionManager = new TransactionManagerImpl();
            transactionManager.begin(albumDao, genreDao, authorDao, userAlbumDao);

            List<Album> albumList = albumDao.getAll();

            userAlbumOptional = userAlbumDao.getByPK(decodedJWT.getClaim("userId").asInt());

            List<Album> rightAlbumList = new ArrayList<>();

            userAlbumOptional.ifPresent(
                    u -> {
                        for (Album c : albumList) {
                            if (!u.getAlbumIdList().contains(c.getId())) {
                                rightAlbumList.add(c);
                            }
                        }
                    }
            );


            for (Album c : rightAlbumList) {
                AlbumDto albumDto = new AlbumDto();
                albumDto.setName(c.getName());
                albumDto.setPrice(c.getPrice());

                try {
                    Optional<Author> authorOptional = authorDao.getByPK(c.getAuthor_id());
                    if (authorOptional.isPresent()) {
                        Author a = authorOptional.get();
                        albumDto.setAuthor(a.getPseudonim());
                    }

                    Optional<Genre> genreOptional = genreDao.getByPK(c.getGenre_id());
                    if (genreOptional.isPresent()) {
                        Genre genre = genreOptional.get();
                        albumDto.setGenre(genre.getGenre());
                    }
                } catch (DaoException e) {
                    throw new ServiceException(MessageFormat.format(ExceptionDirector.EXC_MSG, ExceptionDirector.IMPOSSIBLE_GET_DATA) + e.getMessage());
                }

                albumDtoList.add(albumDto);
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

        return albumDtoList;
    }

    @Override
    public List<AlbumDto> getAllUserAlbumDto(HttpServletRequest request) throws ServiceException {
        Cookie[] cookies = request.getCookies();
        Optional<Cookie> cookieToken = Arrays.stream(cookies).filter(s -> s.getName().equals("token")).findFirst();
        DecodedJWT decodedJWT = JWT.decode(cookieToken.get().getValue());

        TransactionManager transactionManager = new TransactionManagerImpl();

        List<AlbumDto> albumDtoList = new ArrayList<>();

        try {
            GenericDao<UserAlbum, Integer> userAlbumDao = JdbcDaoFactory.getInstance().getTransactionalDao(UserAlbum.class);
            GenericDao<Album, Integer> albumDao = JdbcDaoFactory.getInstance().getTransactionalDao(Album.class);
            GenericDao<Author, Integer> authorDao = JdbcDaoFactory.getInstance().getTransactionalDao(Author.class);
            GenericDao<Genre, Integer> genreDao = JdbcDaoFactory.getInstance().getTransactionalDao(Genre.class);

            transactionManager.begin(userAlbumDao, albumDao, authorDao, genreDao);

            Optional<UserAlbum> userAlbumOptional = userAlbumDao.getByPK(decodedJWT.getClaim("userId").asInt());

            if (userAlbumOptional.isPresent()) {
                List<Integer> userAlbumIdList = userAlbumOptional.get().getAlbumIdList();
                for (Integer u : userAlbumIdList) {
                    try {
                        Optional<Album> albumOptional = albumDao.getByPK(u);
                        if (albumOptional.isPresent()) {
                            try {
                                Optional<Author> authorOptional = authorDao.getByPK(albumOptional.get().getAuthor_id());
                                if (authorOptional.isPresent()) {
                                    try {
                                        Optional<Genre> genreOptional = genreDao.getByPK(albumOptional.get().getGenre_id());
                                        if (genreOptional.isPresent()) {
                                            AlbumDto albumDto = new AlbumDto();
                                            albumDto.setName(albumOptional.get().getName());
                                            albumDto.setYear(albumOptional.get().getYear());
                                            albumDto.setGenre(genreOptional.get().getGenre());
                                            albumDto.setAuthor(authorOptional.get().getPseudonim());

                                            albumDtoList.add(albumDto);
                                        }
                                    } catch (DaoException e) {
                                        throw new ServiceException(e.getMessage());
                                    }
                                }
                            } catch (DaoException e) {
                                throw new ServiceException(e.getMessage());
                            }

                        }
                    } catch (DaoException e) {
                        e.printStackTrace();
                    }
                }
            }

            transactionManager.commit();
        } catch (DaoException e) {
            try {
                transactionManager.rollBack();
            } catch (DaoException e1) {
                e1.printStackTrace();
            }
            throw new ServiceException(e.getMessage());
        } finally {
            try {
                transactionManager.end();
            } catch (DaoException e) {
                e.printStackTrace();
            }
        }

        return albumDtoList;
    }

    @Override
    public boolean addAlbum(HttpServletRequest request) throws ServiceException {
        String albumName = request.getParameter("albumName");
        String albumDescription = request.getParameter("albumDescription");

        int albumPrice = Integer.valueOf(request.getParameter("albumPrice"));
        int albumYear = Integer.valueOf(request.getParameter("albumYear"));
        int authorId = Integer.valueOf(request.getParameter("authorId"));
        int genreId = Integer.valueOf(request.getParameter("genreId"));

        Album album = new Album(albumDescription, albumPrice, albumName, authorId, genreId, albumYear);

        try {
            JdbcDaoFactory.getInstance().getDao(Album.class).insert(album);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }

        return true;
    }
}
