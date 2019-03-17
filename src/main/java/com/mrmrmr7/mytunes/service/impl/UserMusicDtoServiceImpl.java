package com.mrmrmr7.mytunes.service.impl;

import com.mrmrmr7.mytunes.dto.UserMusicDto;
import com.mrmrmr7.mytunes.service.*;

import javax.servlet.http.HttpServletRequest;

public class UserMusicDtoServiceImpl implements UserMusicDtoService {
    @Override
    public UserMusicDto getUserMusicDto(HttpServletRequest request) throws ServiceException {
        UserMusicDto userMusicDto = new UserMusicDto();
        AlbumDtoService albumDtoService = new AlbumDtoServiceImpl();
        CompositionDtoService compositionDtoService = new CompositionDtoServiceImpl();
        MusicSelectionInfoService musicSelectionInfoService = new MusicSelectionInfoServiceImpl();

        userMusicDto.setAlbumDtoList(albumDtoService.getAllUserAlbumDto(request));
        userMusicDto.setCompositionDtoList(compositionDtoService.getAllUserCompositionDto(request));
        userMusicDto.setMusicSelectionInfoList(musicSelectionInfoService.getAllUserMusicSelectionInfo(request));

        return userMusicDto;
    }
}
