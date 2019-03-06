package com.mrmrmr7.mytunes.controller.command.impl.bonus;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.controller.command.ResponseContent;
import com.mrmrmr7.mytunes.controller.command.Router;
import com.mrmrmr7.mytunes.dao.ConnectionPoolFactory;
import com.mrmrmr7.mytunes.dao.ConnectionPoolType;
import com.mrmrmr7.mytunes.dao.exception.DAOException;
import com.mrmrmr7.mytunes.dao.impl.BonusDAO;
import com.mrmrmr7.mytunes.util.PageDirector;

import javax.servlet.http.HttpServletRequest;

public class CommandBonusDelete implements Command {

    private static final String PARAMETER_ID = "id";

    @Override
    public ResponseContent process(HttpServletRequest request) {
        System.out.println(CommandDirector.BONUS_DELETE.getValue() + " command detected");

        BonusDAO bonusDAO = new BonusDAO();

        try {
            bonusDAO.setConnection(ConnectionPoolFactory
                    .getInstance()
                    .getConnectionPool(ConnectionPoolType.MYSQL)
                    .getConnection());

            bonusDAO.delete(Integer.valueOf(request.getParameter(PARAMETER_ID)));
        } catch (DAOException e) {
            System.out.println("Impossible to find bonus with such id");
        } finally {
            bonusDAO.closeConnection();
        }

        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(
                new Router(
                        request.getContextPath() + PageDirector.CRUD_BONUS.getValue(),
                        Router.Type.REDIRECT
                )
        );
        return responseContent;
    }
}