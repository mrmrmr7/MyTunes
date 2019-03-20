package com.mrmrmr7.mytunes.service;

import com.mrmrmr7.mytunes.dto.MusicSelectionFeedbackDto;
import com.mrmrmr7.mytunes.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface MusicSelectionFeedbackService {

    List<MusicSelectionFeedbackDto> getFeedbackDtoByMusicSelectionId(HttpServletRequest request) throws ServiceException;

    boolean addMusicSelectionFeedback(HttpServletRequest request) throws ServiceException;

    List<MusicSelectionFeedbackDto> getUserMusicSelectionFeedbackList(HttpServletRequest request) throws ServiceException;
}
