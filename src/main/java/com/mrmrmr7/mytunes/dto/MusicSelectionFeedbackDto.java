package com.mrmrmr7.mytunes.dto;

import java.sql.Timestamp;

public class MusicSelectionFeedbackDto {

    private String musicSelectionName;
    private String userName;
    private String description;
    private Timestamp timestamp;

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

    public String getMusicSelectionName() {
        return musicSelectionName;
    }

    public void setMusicSelectionName(String musicSelectionName) {
        this.musicSelectionName = musicSelectionName;
    }

}
