package com.mrmrmr7.mytunes.controller.command.impl.view;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.entity.Bonus;
import com.mrmrmr7.mytunes.entity.ResponseContent;
import com.mrmrmr7.mytunes.entity.Router;
import com.mrmrmr7.mytunes.service.BonusService;
import com.mrmrmr7.mytunes.service.exception.ServiceException;
import com.mrmrmr7.mytunes.service.impl.BonusServiceImpl;
import com.mrmrmr7.mytunes.util.PageDirector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ViewCreateMusicSelectionPageCommand implements Command {

    @Override
    public ResponseContent process(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(CommandDirector.ADMIN_VIEW_CREATE_MUSIC_SELECTION_PAGE.getValue() + " command detected");
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(PageDirector.VIEW_CREATE_MUSIC_SELECTION_PAGE, Router.Type.FORWARD));
        return responseContent;
    }
}
