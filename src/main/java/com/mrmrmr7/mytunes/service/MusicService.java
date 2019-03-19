package com.mrmrmr7.mytunes.service;

import com.mrmrmr7.mytunes.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

public interface MusicService {

    boolean buyComposition(HttpServletRequest request) throws ServiceException;
    boolean buyAlbum(HttpServletRequest request) throws ServiceException;
    boolean buyMusicSelection(HttpServletRequest request) throws ServiceException;

}
