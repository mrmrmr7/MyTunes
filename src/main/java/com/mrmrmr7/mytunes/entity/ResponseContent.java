package com.mrmrmr7.mytunes.entity;

import com.mrmrmr7.mytunes.entity.Router;

/**
 * Provide response content to View layer
 */
public class ResponseContent {
    private Router router;

    public ResponseContent() { }

    public Router getRouter() {
        return router;
    }

    public void setRouter(Router router) {
        this.router = router;
    }

}