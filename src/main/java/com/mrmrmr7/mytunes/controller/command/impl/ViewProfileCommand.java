package com.mrmrmr7.mytunes.controller.command.impl;

import com.mrmrmr7.mytunes.controller.command.Command;
import com.mrmrmr7.mytunes.dto.UserDto;
import com.mrmrmr7.mytunes.entity.ResponseContent;

import javax.servlet.http.HttpServletRequest;

public class ViewProfileCommand implements Command {

    @Override
    public ResponseContent process(HttpServletRequest request) {
        UserDto userData;
        return null;
    }
}
