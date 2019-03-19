package com.mrmrmr7.mytunes.service;

import com.mrmrmr7.mytunes.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

public interface BalanceService {
    long updateBalance (HttpServletRequest request) throws ServiceException;
    String getBalanceById(HttpServletRequest request) throws ServiceException;
}
