package com.mrmrmr7.mytunes.controller.command.impl.view;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.entity.ResponseContent;
import com.mrmrmr7.mytunes.entity.Router;
import com.mrmrmr7.mytunes.util.PageDirector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewUpdateMusicSelectionPageCommand implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request, HttpServletResponse response) {
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(PageDirector.VIEW_UPDATE_MUSIC_SELECTION_PAGE, Router.Type.FORWARD));
        return responseContent;
    }
}
