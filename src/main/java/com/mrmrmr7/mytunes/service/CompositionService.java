package com.mrmrmr7.mytunes.service;

import com.mrmrmr7.mytunes.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

public interface CompositionService {
    boolean addComposition (HttpServletRequest request) throws ServiceException;
}
