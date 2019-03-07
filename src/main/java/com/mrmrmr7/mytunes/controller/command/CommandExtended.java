package com.mrmrmr7.mytunes.controller.command;

import com.mrmrmr7.mytunes.entity.ResponseContent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommandExtended extends Command {

    ResponseContent process(HttpServletRequest request, HttpServletResponse httpServletResponse);
}
