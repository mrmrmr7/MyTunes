package com.mrmrmr7.mytunes.controller.command.impl.albumfeedback;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.entity.ResponseContent;
import com.mrmrmr7.mytunes.entity.Router;
import com.mrmrmr7.mytunes.dao.ConnectionPoolFactory;
import com.mrmrmr7.mytunes.dao.ConnectionPoolType;
import com.mrmrmr7.mytunes.dao.exception.DAOException;
import com.mrmrmr7.mytunes.dao.impl.AlbumFeedbackDAO;
import com.mrmrmr7.mytunes.entity.AlbumFeedback;
import com.mrmrmr7.mytunes.util.PageDirector;

import javax.servlet.http.HttpServletRequest;

public class CommandAlbumFeedbackInsert implements Command {

    private static final String PARAMETER_ALBUM_FEEDBACK = "albumFeedbackFeedback";

    @Override
    public ResponseContent process(HttpServletRequest request) {
        System.out.println(CommandDirector.ALBUM_FEEDBACK_INSERT + " command detected");

        AlbumFeedback albumFeedback = new AlbumFeedback(
                3,
                request.getParameter(PARAMETER_ALBUM_FEEDBACK)
        );

        AlbumFeedbackDAO albumFeedbackDAO = new AlbumFeedbackDAO();

        try {
            albumFeedbackDAO.setConnection(ConnectionPoolFactory
                    .getInstance()
                    .getConnectionPool(ConnectionPoolType.MYSQL)
                    .getConnection());

            albumFeedbackDAO.insert(albumFeedback);
        } catch (DAOException e) {
            System.out.println("Impossible to find albumFeedback with such id");
        } finally {
            albumFeedbackDAO.closeConnection();
        }

        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(
                new Router(PageDirector.CRUD_ALBUM_FEEDBACK, Router.Type.REDIRECT)
        );
        return responseContent;
    }
}
