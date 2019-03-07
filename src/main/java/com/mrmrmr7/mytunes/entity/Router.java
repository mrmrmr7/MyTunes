package com.mrmrmr7.mytunes.entity;

import com.mrmrmr7.mytunes.util.PageDirector;

import java.util.Arrays;

/**
 * Provide route to jsp page
 */
public class Router {
    private String route;
    private Type type;

    public enum Type {
        FORWARD("forward"), REDIRECT ("redirect");
        private String value;

        Type(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public Router(PageDirector route, Type type) {
        this.route = route.getValue();
        this.type = type;
    }

    public String getRoute() {
        return route;
    }

    public String getType() {
        return type.getValue();
    }

}