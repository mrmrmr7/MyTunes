package com.mrmrmr7.mytunes.service;

import com.mrmrmr7.mytunes.dto.CompositionFeedbackDto;
import com.mrmrmr7.mytunes.entity.CompositionFeedback;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface CompositionFeedbackDtoService {
    List<CompositionFeedbackDto> getFeedbackByCompositionId(HttpServletRequest request) throws ServiceException;
}
