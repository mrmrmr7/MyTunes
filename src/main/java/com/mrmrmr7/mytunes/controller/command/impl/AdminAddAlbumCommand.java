package com.mrmrmr7.mytunes.controller.command.impl;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.entity.Author;
import com.mrmrmr7.mytunes.entity.ResponseContent;
import com.mrmrmr7.mytunes.entity.Router;
import com.mrmrmr7.mytunes.service.AlbumService;
import com.mrmrmr7.mytunes.service.AuthorService;
import com.mrmrmr7.mytunes.service.GenreService;
import com.mrmrmr7.mytunes.service.exception.ServiceException;
import com.mrmrmr7.mytunes.service.impl.AlbumServiceImpl;
import com.mrmrmr7.mytunes.service.impl.AuthorServiceImpl;
import com.mrmrmr7.mytunes.service.impl.GenreServiceImpl;
import com.mrmrmr7.mytunes.util.PageDirector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AdminAddAlbumCommand implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        ResponseContent responseContent = new ResponseContent();
        AlbumService albumService = new AlbumServiceImpl();
        GenreService genreService = new GenreServiceImpl();
        AuthorService authorService = new AuthorServiceImpl();

        List<Author> authorList = authorService.getAuthorList();
        request.setAttribute("authorList", authorList);
        request.setAttribute("genreList", genreService.getGenreList());
        request.setAttribute("success", albumService.addAlbum(request));


        responseContent.setRouter(new Router(PageDirector.VIEW_ADMIN_CREATE_ALBUM_PAGE, Router.Type.FORWARD));
        return responseContent;
    }
}
