package com.mrmrmr7.mytunes.service;

import com.mrmrmr7.mytunes.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

public interface BalanceService {
    boolean updateBalance (int userId, int paymentCount) throws ServiceException;
    String getBalanceById(HttpServletRequest request) throws ServiceException;
}
