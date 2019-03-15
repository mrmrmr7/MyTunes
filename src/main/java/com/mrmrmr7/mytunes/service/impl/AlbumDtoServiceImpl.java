package com.mrmrmr7.mytunes.service.impl;

import com.mrmrmr7.mytunes.dao.GenericDao;
import com.mrmrmr7.mytunes.dao.TransactionManager;
import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.dao.impl.JdbcDaoFactory;
import com.mrmrmr7.mytunes.dao.impl.TransactionManagerImpl;
import com.mrmrmr7.mytunes.dto.AlbumDto;
import com.mrmrmr7.mytunes.dto.CompositionDto;
import com.mrmrmr7.mytunes.entity.*;
import com.mrmrmr7.mytunes.service.AlbumDtoService;
import com.mrmrmr7.mytunes.service.CompositionDtoService;
import com.mrmrmr7.mytunes.service.ServiceException;
import com.mrmrmr7.mytunes.util.ProtectionUtil;
import io.jsonwebtoken.Claims;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AlbumDtoServiceImpl implements AlbumDtoService {

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
                    e.printStackTrace();
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
    public List<AlbumDto> getAllNotUserAlbumDto(HttpServletRequest request) throws ServiceException { //FIXME bad name
        List<AlbumDto> albumDtoList = new ArrayList<>();
        Optional<UserAlbum> userAlbumOptional = null;

        Cookie[] cookieArray = request.getCookies();
        Claims claims = ProtectionUtil.getClaimsFromCookies(cookieArray);

        TransactionManager transactionManager = new TransactionManagerImpl();
        try {
            GenericDao albumDao = JdbcDaoFactory.getInstance().getTransactionalDao(Album.class);
            GenericDao genreDao = JdbcDaoFactory.getInstance().getTransactionalDao(Genre.class);
            GenericDao authorDao = JdbcDaoFactory.getInstance().getTransactionalDao(Author.class);
            GenericDao userAlbumDao = JdbcDaoFactory.getInstance().getTransactionalDao(UserAlbum.class);

            transactionManager = new TransactionManagerImpl();
            transactionManager.begin(albumDao, genreDao, authorDao, userAlbumDao);

            List<Album> albumList = albumDao.getAll();

            userAlbumOptional = userAlbumDao.getByPK(claims.get("userId", Integer.class));

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
                    e.printStackTrace();
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
}
