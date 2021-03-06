package com.mrmrmr7.mytunes.service.impl;

import com.mrmrmr7.mytunes.dto.UserMusicDto;
import com.mrmrmr7.mytunes.service.*;
import com.mrmrmr7.mytunes.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

public class UserMusicDtoServiceImpl implements UserMusicDtoService {
    @Override
    public UserMusicDto getUserMusicDto(HttpServletRequest request) throws ServiceException {
        UserMusicDto userMusicDto = new UserMusicDto();
        AlbumService albumService = new AlbumServiceImpl();
        CompositionService compositionDtoService = new CompositionServiceImpl();
        MusicSelectionService musicSelectionService = new MusicSelectionServiceImpl();

        userMusicDto.setAlbumDtoList(albumService.getAllUserAlbumDto(request));
        userMusicDto.setCompositionDtoList(compositionDtoService.getAllUserCompositionDto(request));
        userMusicDto.setMusicSelectionInfoList(musicSelectionService.getAllUserMusicSelectionInfo(request));

        return userMusicDto;
    }
}
