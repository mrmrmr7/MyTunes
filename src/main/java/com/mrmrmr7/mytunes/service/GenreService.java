package com.mrmrmr7.mytunes.service;

import com.mrmrmr7.mytunes.entity.Genre;
import com.mrmrmr7.mytunes.service.exception.ServiceException;

import java.util.List;

public interface GenreService {
    List<Genre> getGenreList() throws ServiceException;
}
