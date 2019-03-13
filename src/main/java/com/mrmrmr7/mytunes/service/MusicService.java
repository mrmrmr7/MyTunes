package com.mrmrmr7.mytunes.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface MusicService {

    boolean buyComposition(HttpServletRequest request) throws ServiceException;
    boolean buyAlbum(HttpServletRequest request) throws ServiceException;
    boolean buyMusicSelection(HttpServletRequest request) throws ServiceException;

}
