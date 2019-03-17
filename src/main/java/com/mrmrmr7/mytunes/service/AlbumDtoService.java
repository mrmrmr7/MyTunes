package com.mrmrmr7.mytunes.service;

import com.mrmrmr7.mytunes.dto.AlbumDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface AlbumDtoService {
    public List<AlbumDto> getAllAlbumDto() throws ServiceException;
    public List<AlbumDto> getAllNotUserAlbumDto(HttpServletRequest request) throws ServiceException;
    public List<AlbumDto> getAllUserAlbumDto(HttpServletRequest request) throws ServiceException;
}
