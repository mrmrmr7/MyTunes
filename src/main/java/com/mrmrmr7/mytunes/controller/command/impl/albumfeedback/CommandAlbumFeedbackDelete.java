package com.mrmrmr7.mytunes.controller.command.impl.albumFeedbackfeedback;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.entity.ResponseContent;
import com.mrmrmr7.mytunes.entity.Router;
import com.mrmrmr7.mytunes.dao.impl.ConnectionPoolFactory;
import com.mrmrmr7.mytunes.dao.ConnectionPoolType;
import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.dao.impl.AlbumFeedbackDao;
import com.mrmrmr7.mytunes.util.PageDirector;

import javax.servlet.http.HttpServletRequest;

public class CommandAlbumFeedbackDelete implements Command {

    private static final String PARAMETER_ID = "id";

    @Override
    public ResponseContent process(HttpServletRequest request) {
        System.out.println(CommandDirector.ALBUM_FEEDBACK_DELETE.getValue() + " command detected");

        AlbumFeedbackDao albumFeedbackDAO = new AlbumFeedbackDao();

        try {
            albumFeedbackDAO.setConnection(ConnectionPoolFactory
                    .getInstance()
                    .getConnectionPool(ConnectionPoolType.MYSQL)
                    .getConnection());

            albumFeedbackDAO.delete(Integer.valueOf(request.getParameter(PARAMETER_ID)));
        } catch (DaoException e) {
            System.out.println("Impossible to find albumFeedback with such id");
        } finally {
            albumFeedbackDAO.closeConnection();
        }

        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(
                new Router(PageDirector.CRUD_ALBUM_FEEDBACK,Router.Type.REDIRECT)
        );
        return responseContent;
    }
}
