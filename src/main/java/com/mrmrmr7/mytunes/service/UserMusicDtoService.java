package com.mrmrmr7.mytunes.service;

import com.mrmrmr7.mytunes.dto.UserMusicDto;
import com.mrmrmr7.mytunes.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

public interface UserMusicDtoService {
    UserMusicDto getUserMusicDto(HttpServletRequest request) throws ServiceException;
}
