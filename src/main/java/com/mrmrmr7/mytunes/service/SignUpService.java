package com.mrmrmr7.mytunes.service;

import javax.servlet.http.HttpServletRequest;
import java.util.Properties;

public interface SignUpService {

    Properties getProperties();

    boolean sendSignUpMessage(HttpServletRequest request) throws ServiceException;

    boolean finishSignUp(HttpServletRequest request) throws ServiceException;
}
