package com.mrmrmr7.mytunes.service;

import com.mrmrmr7.mytunes.entity.User;
import com.mrmrmr7.mytunes.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.Properties;

public interface SignUpService {

    Properties getProperties();

    boolean sendSignUpMessage(User user) throws ServiceException;

    boolean finishSignUp(HttpServletRequest request) throws ServiceException;
}
