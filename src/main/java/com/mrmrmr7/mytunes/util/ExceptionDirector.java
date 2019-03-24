package com.mrmrmr7.mytunes.util;

public enum  ExceptionDirector {
    DAO_USR_GBL ("dao.user.getByLogin"),
    SRV_USR_IRU ("service.userServiceImpl.isRightUser"),
    CMD_SIN_PRC ("command.signin.process");


    private String value;

    ExceptionDirector(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
