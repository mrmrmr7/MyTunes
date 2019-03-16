package com.mrmrmr7.mytunes.service;

import com.mrmrmr7.mytunes.dto.AlbumFeedbackDto;
import com.mrmrmr7.mytunes.dto.CompositionFeedbackDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface AlbumFeedbackDtoService {
    List<AlbumFeedbackDto> getFeedbackByAlbumId(HttpServletRequest request) throws ServiceException;
}
