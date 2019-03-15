package com.mrmrmr7.mytunes.service;

import com.mrmrmr7.mytunes.entity.MusicSelectionInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface MusicSelectionInfoService {

    List<MusicSelectionInfo> getAllMusicSelectionInfo() throws ServiceException;
    List<MusicSelectionInfo> getAllNotUserMusicSelectionInfo(HttpServletRequest request) throws ServiceException;
}
