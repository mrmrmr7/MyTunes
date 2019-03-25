package com.mrmrmr7.mytunes.builder;

import com.mrmrmr7.mytunes.builder.exception.BuilderException;

import javax.servlet.http.HttpServletRequest;

public interface Builder<T> {
    T build(HttpServletRequest request) throws BuilderException;
}
