package com.mrmrmr7.mytunes.service;

import com.mrmrmr7.mytunes.dto.CompositionDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface CompositionDtoService {

    List<CompositionDto> getAllCompositionDto() throws ServiceException;
    List<CompositionDto> getAllNotUserCompositionDto(HttpServletRequest request) throws ServiceException;
    List<CompositionDto> getAllUserCompositionDto(HttpServletRequest request) throws ServiceException;
}
