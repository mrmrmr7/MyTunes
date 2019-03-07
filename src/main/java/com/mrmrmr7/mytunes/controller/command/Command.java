package com.mrmrmr7.mytunes.controller.command;



import com.mrmrmr7.mytunes.entity.ResponseContent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@FunctionalInterface
public interface Command {
    ResponseContent process(HttpServletRequest request);
}