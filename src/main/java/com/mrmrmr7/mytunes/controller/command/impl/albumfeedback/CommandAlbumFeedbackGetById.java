package com.mrmrmr7.mytunes.controller.command.impl.albumFeedbackfeedback;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.entity.ResponseContent;
import com.mrmrmr7.mytunes.entity.Router;
import com.mrmrmr7.mytunes.dao.ConnectionPoolFactory;
import com.mrmrmr7.mytunes.dao.ConnectionPoolType;
import com.mrmrmr7.mytunes.dao.exception.DAOException;
import com.mrmrmr7.mytunes.dao.impl.AlbumFeedbackDao;
import com.mrmrmr7.mytunes.entity.AlbumFeedback;
import com.mrmrmr7.mytunes.util.PageDirector;

import javax.servlet.http.HttpServletRequest;

public class CommandAlbumFeedbackGetById implements Command {

    private static final String INCLUDE_PATH = "albumfeedback/getbyid";
    private static final String ATTRIBUTE_VIEW_NAME = "viewName";
    private static final String ATTRIBUTE_FEEDBACK = "albumFeedback";
    private static final String PARAMETER_ID = "id";

    @Override
    public ResponseContent process(HttpServletRequest request) {
        System.out.println(CommandDirector.ALBUM_FEEDBACK_GET_BY_ID.getValue() + " command detected");
        AlbumFeedbackDao albumFeedbackDAO = new AlbumFeedbackDao();
        AlbumFeedback albumFeedback = null;
        try {
            albumFeedbackDAO.setConnection(ConnectionPoolFactory
                    .getInstance()
                    .getConnectionPool(ConnectionPoolType.MYSQL)
                    .getConnection());

            albumFeedback = albumFeedbackDAO
                    .getByPK(Integer.valueOf(request.getParameter(PARAMETER_ID)))
                    .get();
        } catch (DAOException e) {
            System.out.println("Impossible to find AlbumFeedback with such id");
        } finally {
            albumFeedbackDAO.closeConnection();
        }
        request.setAttribute(ATTRIBUTE_FEEDBACK, albumFeedback);
        request.setAttribute(ATTRIBUTE_VIEW_NAME, INCLUDE_PATH);
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(PageDirector.VIEW,Router.Type.FORWARD));
        return responseContent;
    }
}
