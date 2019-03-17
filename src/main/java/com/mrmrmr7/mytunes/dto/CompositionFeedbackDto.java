package com.mrmrmr7.mytunes.dto;

import java.sql.Timestamp;

public class CompositionFeedbackDto {
    private String userName;
    private String description;
    private Timestamp timestamp;
    private String compositionName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getCompositionName() {
        return compositionName;
    }

    public void setCompositionName(String compositionName) {
        this.compositionName = compositionName;
    }

}
