package com.mrmrmr7.mytunes.service;

import com.mrmrmr7.mytunes.entity.MusicSelectionInfo;
import com.mrmrmr7.mytunes.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public interface MusicSelectionService {

    Optional<MusicSelectionInfo> getMusicSelectionInfoByName(String name) throws ServiceException;
    boolean createMusicSelection(HttpServletRequest request) throws ServiceException;
    boolean updateMusicSelection(HttpServletRequest request) throws ServiceException;
    boolean insertMusicSelection(HttpServletRequest request) throws ServiceException;
    List<MusicSelectionInfo> getAllMusicSelectionInfo() throws ServiceException;
    List<MusicSelectionInfo> getAllNotUserMusicSelectionInfo(HttpServletRequest request) throws ServiceException;
    List<MusicSelectionInfo> getAllUserMusicSelectionInfo(HttpServletRequest request) throws ServiceException;
}
