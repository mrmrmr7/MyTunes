package com.mrmrmr7.mytunes.service;

import com.mrmrmr7.mytunes.dto.MusicSelectionFeedbackDto;
import com.mrmrmr7.mytunes.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface MusicSelectionFeedbackDtoService {
    List<MusicSelectionFeedbackDto> getFeedbackByMusicSelectionId(HttpServletRequest request) throws ServiceException;
}
