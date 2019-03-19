package com.mrmrmr7.mytunes.service;

import com.mrmrmr7.mytunes.dto.AlbumDto;
import com.mrmrmr7.mytunes.entity.Album;
import com.mrmrmr7.mytunes.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface AlbumService {
    List<Album> getAllAlbum() throws ServiceException;
    List<AlbumDto> getAllAlbumDto() throws ServiceException;
    List<AlbumDto> getAllNotUserAlbumDto(HttpServletRequest request) throws ServiceException;
    List<AlbumDto> getAllUserAlbumDto(HttpServletRequest request) throws ServiceException;

    boolean addAlbum(HttpServletRequest request) throws ServiceException;
}
