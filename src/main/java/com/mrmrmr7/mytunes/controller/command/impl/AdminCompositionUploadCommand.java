package com.mrmrmr7.mytunes.controller.command.impl;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.entity.Album;
import com.mrmrmr7.mytunes.entity.Author;
import com.mrmrmr7.mytunes.entity.ResponseContent;
import com.mrmrmr7.mytunes.entity.Router;
import com.mrmrmr7.mytunes.service.AlbumService;
import com.mrmrmr7.mytunes.service.AuthorService;
import com.mrmrmr7.mytunes.service.CompositionService;
import com.mrmrmr7.mytunes.service.exception.ServiceException;
import com.mrmrmr7.mytunes.service.impl.AlbumServiceImpl;
import com.mrmrmr7.mytunes.service.impl.AuthorServiceImpl;
import com.mrmrmr7.mytunes.service.impl.CompositionServiceImpl;
import com.mrmrmr7.mytunes.util.PageDirector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AdminCompositionUploadCommand implements Command {

    @Override
    public ResponseContent process(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(CommandDirector.ADMIN_UPLOAD_COMPOSITION.getValue() + " command detected");

        CompositionService compositionService = new CompositionServiceImpl();
        AlbumService albumService = new AlbumServiceImpl();
        AuthorService authorService = new AuthorServiceImpl();

        try {
            List<Album> albumList = albumService.getAllAlbum();
            request.setAttribute("albumList", albumList);
            List<Author> authorList = authorService.getAuthorList();
            request.setAttribute("authorList", authorList);
            request.setAttribute("success", compositionService.addComposition(request));
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(PageDirector.VIEW_UPLOAD_COMPOSITION_PAGE, Router.Type.FORWARD));
        return responseContent;
    }
}
