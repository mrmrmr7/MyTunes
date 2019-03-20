package com.mrmrmr7.mytunes.service;

import com.mrmrmr7.mytunes.dto.CompositionDto;
import com.mrmrmr7.mytunes.entity.Composition;
import com.mrmrmr7.mytunes.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public interface CompositionService {

    List<CompositionDto> getAllCompositionDto() throws ServiceException;
    List<CompositionDto> getAllNotUserCompositionDto(HttpServletRequest request) throws ServiceException;
    List<CompositionDto> getAllUserCompositionDto(HttpServletRequest request) throws ServiceException;
    Optional<Composition> getCompositionDtoByName(String name) throws ServiceException;
    boolean addComposition (HttpServletRequest request) throws ServiceException;
    boolean updateComposition(HttpServletRequest request) throws ServiceException;
}
