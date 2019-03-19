package com.mrmrmr7.mytunes.controller.command.impl;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.entity.ResponseContent;
import com.mrmrmr7.mytunes.entity.Router;
import com.mrmrmr7.mytunes.util.PageDirector;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ViewAdminCreateMusicSelectionPageCommand implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request) { //FIXME realize it after!
        System.out.println(CommandDirector.VIEW_ADMIN_CREATE_MUSIC_SELECTION_PAGE.getValue() + " command detected");
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(PageDirector.VIEW_ADMIN_CREATE_MUSIC_SELECTION_PAGE, Router.Type.FORWARD));
        return responseContent;
    }
}
