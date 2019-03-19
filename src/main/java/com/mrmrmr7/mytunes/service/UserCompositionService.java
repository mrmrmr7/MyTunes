package com.mrmrmr7.mytunes.service;

import com.mrmrmr7.mytunes.entity.Composition;
import com.mrmrmr7.mytunes.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserCompositionService {
    List<Composition> showUserComposition(HttpServletRequest request) throws ServiceException;
}
