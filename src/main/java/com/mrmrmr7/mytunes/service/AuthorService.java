package com.mrmrmr7.mytunes.service;

import com.mrmrmr7.mytunes.entity.Author;
import com.mrmrmr7.mytunes.service.exception.ServiceException;

import javax.xml.ws.Service;
import java.util.List;

public interface AuthorService {
    List<Author> getAuthorList() throws ServiceException;
}
