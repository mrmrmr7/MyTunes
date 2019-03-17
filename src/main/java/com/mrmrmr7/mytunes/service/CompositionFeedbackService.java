package com.mrmrmr7.mytunes.service;

import com.mrmrmr7.mytunes.dto.CompositionFeedbackDto;
import com.mrmrmr7.mytunes.entity.CompositionFeedback;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface CompositionFeedbackService {

    List<CompositionFeedbackDto> getFeedbackDtoByCompositionId(HttpServletRequest request) throws ServiceException;

    boolean addCompositionFeedback(HttpServletRequest request) throws ServiceException;

    List<CompositionFeedbackDto> getUserCompositionFeedbackList(HttpServletRequest request) throws ServiceException;
}
