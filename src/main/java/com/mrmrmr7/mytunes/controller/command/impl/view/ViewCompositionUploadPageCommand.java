package com.mrmrmr7.mytunes.controller.command.impl.view;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.entity.Album;
import com.mrmrmr7.mytunes.entity.Author;
import com.mrmrmr7.mytunes.entity.ResponseContent;
import com.mrmrmr7.mytunes.entity.Router;
import com.mrmrmr7.mytunes.service.AlbumService;
import com.mrmrmr7.mytunes.service.AuthorService;
import com.mrmrmr7.mytunes.service.exception.ServiceException;
import com.mrmrmr7.mytunes.service.impl.AlbumServiceImpl;
import com.mrmrmr7.mytunes.service.impl.AuthorServiceImpl;
import com.mrmrmr7.mytunes.util.PageDirector;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ViewCompositionUploadPageCommand implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request) {
        System.out.println(CommandDirector.ADMIN_VIEW_UPLOAD_COMPOSITION_PAGE.getValue() + " command detected");
        AlbumService albumService = new AlbumServiceImpl();
        AuthorService authorService = new AuthorServiceImpl();

        try {
            List<Album> albumList = albumService.getAllAlbum();
            request.setAttribute("albumList", albumList);
            List<Author> authorList = authorService.getAuthorList();
            request.setAttribute("authorList", authorList);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(PageDirector.VIEW_UPLOAD_COMPOSITION_PAGE, Router.Type.FORWARD));
        return responseContent;
    }
}
