package com.mrmrmr7.mytunes.service.impl;

import com.mrmrmr7.mytunes.dao.GenericDao;
import com.mrmrmr7.mytunes.dao.TransactionManager;
import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.dao.impl.JdbcDaoFactory;
import com.mrmrmr7.mytunes.dao.impl.TransactionManagerImpl;
import com.mrmrmr7.mytunes.entity.Album;
import com.mrmrmr7.mytunes.entity.Composition;
import com.mrmrmr7.mytunes.service.CompositionService;
import com.mrmrmr7.mytunes.service.exception.ServiceException;
import sun.rmi.server.InactiveGroupException;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class CompositionServiceImpl implements CompositionService {
    @Override
    public boolean addComposition(HttpServletRequest request) throws ServiceException {
        String compositionName = request.getParameter("compositionName");
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
            } catch (DaoException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                transactionManager.end();
            } catch (DaoException e) {
                e.printStackTrace();
            }
        }

        return true;
    }
}
