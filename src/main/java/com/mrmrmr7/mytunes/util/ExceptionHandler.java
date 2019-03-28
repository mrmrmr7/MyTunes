package com.mrmrmr7.mytunes.util;

import com.mrmrmr7.mytunes.entity.ExceptionDescription;
import com.mrmrmr7.mytunes.entity.ResponseContent;
import com.mrmrmr7.mytunes.entity.Router;

public class ExceptionHandler {

    public static ExceptionDescription getDescription(String exceptionMessage) {
        ExceptionDescription exceptionDescription = new ExceptionDescription();

        String code = exceptionMessage.substring(6,10);

        exceptionDescription.setErrorCode(code);
        ResponseContent responseContent = new ResponseContent();

        responseContent.setRouter(new Router(PageDirector.ERROR_PAGE.getValue(), Router.Type.FORWARD));

        exceptionDescription.setErrorCode(code);
        exceptionDescription.setMessage(ExceptionDirector.getMessageByCode(code));
        exceptionDescription.setResponseContent(responseContent);

        return exceptionDescription;
    }
}
