package com.mrmrmr7.mytunes.service;

import com.mrmrmr7.mytunes.dto.AlbumFeedbackDto;
import com.mrmrmr7.mytunes.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface AlbumFeedbackService {


    List<AlbumFeedbackDto> getFeedbackDtoByAlbumId(HttpServletRequest request) throws ServiceException;

    boolean addAlbumFeedback(HttpServletRequest request) throws ServiceException;

    List<AlbumFeedbackDto> getUserAlbumFeedbackList(HttpServletRequest request) throws ServiceException;
}
