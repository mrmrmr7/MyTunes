package com.mrmrmr7.mytunes.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface BalanceService {
    String getBalanceById(HttpServletRequest request) throws ServiceException;
}
