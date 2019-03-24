package com.mrmrmr7.mytunes.util;

import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.entity.ExceptionDescription;
import com.mrmrmr7.mytunes.entity.ResponseContent;
import com.mrmrmr7.mytunes.entity.Router;

import java.util.Arrays;
import java.util.Collections;

public class ExceptionHandler {
    public static ExceptionDescription getDescription(String exceptionMessage) {
        String[] splitedMessage = exceptionMessage.split(":");
        StringBuilder message = new StringBuilder();

        Collections.reverse(Arrays.asList(splitedMessage));

        for (String msg : splitedMessage) {
            String[] parts = msg.split(".");
            message.append("Layer: " + parts[2] + "\n");
            message.append("Class: " + parts[1] + "\n");
            message.append("Method: " + parts[0] + "\n");
        }

        String commandName = splitedMessage[2].split(".")[1];
        String code = CommandDirector.getCodeByValue(commandName);
        ResponseContent responseContent = new ResponseContent();

        responseContent.setRouter(new Router(PageDirector.ERROR, Router.Type.FORWARD));

        ExceptionDescription exceptionDescription = new ExceptionDescription();

        exceptionDescription.setErrorCode(code);
        exceptionDescription.setMessage(message.toString());
        exceptionDescription.setResponseContent(responseContent);

        return exceptionDescription;
    }
}
