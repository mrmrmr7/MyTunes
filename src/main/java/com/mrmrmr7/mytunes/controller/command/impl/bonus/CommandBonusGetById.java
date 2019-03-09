package com.mrmrmr7.mytunes.controller.command.impl.bonus;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.entity.ResponseContent;
import com.mrmrmr7.mytunes.entity.Router;
import com.mrmrmr7.mytunes.dao.ConnectionPoolFactory;
import com.mrmrmr7.mytunes.dao.ConnectionPoolType;
import com.mrmrmr7.mytunes.dao.exception.DAOException;
import com.mrmrmr7.mytunes.dao.impl.BonusDao;
import com.mrmrmr7.mytunes.entity.Bonus;
import com.mrmrmr7.mytunes.util.PageDirector;

import javax.servlet.http.HttpServletRequest;

public class CommandBonusGetById implements Command {

    private static final String ATTRIBUTE_BONUS = "bonus";
    private static final String ATTRIBUTE_VIEW_NAME = "viewName";
    private static final Object INCLUDE_PATH = "bonus/getbyid";
    private static final String PARAMETER_ID = "id";

    @Override
    public ResponseContent process(HttpServletRequest request) {
        System.out.println(CommandDirector.BONUS_GET_BY_ID.getValue() + " command detected");
        BonusDao bonusDAO = new BonusDao();
        Bonus bonus = null;
        try {
            bonusDAO.setConnection(ConnectionPoolFactory
                    .getInstance()
                    .getConnectionPool(ConnectionPoolType.MYSQL)
                    .getConnection());

            bonus = bonusDAO.getByPK(Integer.valueOf(request.getParameter(PARAMETER_ID))).get();
        } catch (DAOException e) {
            System.out.println("Impossible to find bonus with such id");
        } finally {
            bonusDAO.closeConnection();
        }

        request.setAttribute(ATTRIBUTE_BONUS, bonus);
        request.setAttribute(ATTRIBUTE_VIEW_NAME, INCLUDE_PATH);
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(PageDirector.VIEW,Router.Type.FORWARD));
        return responseContent;
    }
}
