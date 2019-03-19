package com.mrmrmr7.mytunes.service;

import com.mrmrmr7.mytunes.entity.MusicSelectionInfo;
import com.mrmrmr7.mytunes.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface MusicSelectionInfoService {

    boolean insertMusicSelection(HttpServletRequest request) throws ServiceException;
    List<MusicSelectionInfo> getAllMusicSelectionInfo() throws ServiceException;
    List<MusicSelectionInfo> getAllNotUserMusicSelectionInfo(HttpServletRequest request) throws ServiceException;
    List<MusicSelectionInfo> getAllUserMusicSelectionInfo(HttpServletRequest request) throws ServiceException;
}
