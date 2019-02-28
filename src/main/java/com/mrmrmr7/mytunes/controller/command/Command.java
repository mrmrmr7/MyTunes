package com.mrmrmr7.mytunes.controller.command;



import javax.servlet.http.HttpServletRequest;
@FunctionalInterface
public interface Command {
    ResponseContent process(HttpServletRequest request) throws Exception;
}