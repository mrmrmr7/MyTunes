package com.mrmrmr7.mytunes.controller.command.impl.bonus;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.entity.ResponseContent;
import com.mrmrmr7.mytunes.entity.Router;
import com.mrmrmr7.mytunes.dao.ConnectionPoolFactory;
import com.mrmrmr7.mytunes.dao.ConnectionPoolType;
import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.dao.impl.BonusDao;
import com.mrmrmr7.mytunes.entity.Bonus;
import com.mrmrmr7.mytunes.util.PageDirector;

import javax.servlet.http.HttpServletRequest;

public class CommandBonusInsert implements Command {
    private static final String PARAMETER_BONUS = "bonusBonus";

    @Override
    public ResponseContent process(HttpServletRequest request) {
        System.out.println(CommandDirector.BONUS_INSERT.getValue() + " command detected");

        Bonus bonus = new Bonus(
                request.getParameter(PARAMETER_BONUS)
        );

        BonusDao bonusDAO = new BonusDao();

        try {
            bonusDAO.setConnection(ConnectionPoolFactory
                    .getInstance()
                    .getConnectionPool(ConnectionPoolType.MYSQL)
                    .getConnection());

            bonusDAO.insert(bonus);
        } catch (DaoException e) {
            System.out.println("Impossible to find bonus with such id");
        } finally {
            bonusDAO.closeConnection();
        }

        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(PageDirector.CRUD_BONUS,Router.Type.REDIRECT));
        return responseContent;
    }
}
