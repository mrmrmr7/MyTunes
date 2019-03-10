package com.mrmrmr7.mytunes.controller.command.impl.albumFeedbackfeedback;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.entity.ResponseContent;
import com.mrmrmr7.mytunes.entity.Router;
import com.mrmrmr7.mytunes.dao.ConnectionPoolFactory;
import com.mrmrmr7.mytunes.dao.ConnectionPoolType;
import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.dao.impl.AlbumFeedbackDao;
import com.mrmrmr7.mytunes.entity.AlbumFeedback;
import com.mrmrmr7.mytunes.util.PageDirector;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CommandAlbumFeedbackGetAll implements Command {

    private static final String ATTRIBUTE_VIEW_NAME = "viewName";
    private static final String INCLUDE_PATH = "albumfeedback/getall";
    private static final String ATTRIBUTE_FEEDBACK_LIST = "albumFeedbackList";

    @Override
    public ResponseContent process(HttpServletRequest request) {
        System.out.println(CommandDirector.ALBUM_FEEDBACK_GET_ALL.getValue() + " command detected");
        AlbumFeedbackDao albumFeedbackDAO = new AlbumFeedbackDao();
        List<AlbumFeedback> albumFeedbackList = null;
        try {
            albumFeedbackDAO.setConnection(ConnectionPoolFactory
                    .getInstance()
                    .getConnectionPool(ConnectionPoolType.MYSQL)
                    .getConnection());

            albumFeedbackList = albumFeedbackDAO.getAll();
        } catch (DaoException e) {
            System.out.println("Impossible to find albumFeedback with such id");
        } finally {
            albumFeedbackDAO.closeConnection();
        }
        request.setAttribute(ATTRIBUTE_FEEDBACK_LIST, albumFeedbackList);
        System.out.println(albumFeedbackList);
        request.setAttribute(ATTRIBUTE_VIEW_NAME, INCLUDE_PATH);
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(
                new Router(PageDirector.VIEW,Router.Type.FORWARD)
        );
        return responseContent;
    }
}
