package com.mrmrmr7.mytunes.controller.command.impl;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.dto.CompositionDto;
import com.mrmrmr7.mytunes.entity.*;
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
import java.util.Optional;

public class GetCompositionForUpdateCommand implements Command {
    @Override
    public ResponseContent process(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(CommandDirector.ADMIN_GET_COMPOSITION_FOR_UPDATE.getValue() + " command detected");

        CompositionService compositionService = new CompositionServiceImpl();
        AlbumService albumService = new AlbumServiceImpl();
        AuthorService authorService = new AuthorServiceImpl();

        String name = request.getParameter("compositionName");

        try {
            Optional<Composition> compositionOptional = compositionService.getCompositionDtoByName(name);
            request.setAttribute("composition", compositionOptional.get());

            List<Album> albumList = albumService.getAllAlbum();
            request.setAttribute("albumList", albumList);

            List<Author> authorList = authorService.getAuthorList();
            request.setAttribute("authorList", authorList);

            for (Album each : albumList) {
                if (each.getId() == compositionOptional.get().getAlbum_id()) {
                    request.setAttribute("compositionAuthorId", each.getAuthor_id());
                    request.setAttribute("compositionAlbumId", compositionOptional.get().getAlbum_id());
                    break;
                }
            }

        } catch (ServiceException e) {
            e.printStackTrace();
        }

        request.setAttribute("showInf", true);

        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(PageDirector.VIEW_UPDATE_COMPOSITION_PAGE, Router.Type.FORWARD));
        return responseContent;
    }
}
