package com.mrmrmr7.mytunes.entity;

import com.mrmrmr7.mytunes.dao.Identified;

import java.sql.Timestamp;

public class MusicSelectionFeedback implements Identified<Integer> {
    private Integer id;
    private String feedback;
    private Timestamp timestamp;

    public MusicSelectionFeedback(Integer id, String feedback, Timestamp timestamp) {
        this.id = id;
        this.feedback = feedback;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "MusicSelectionFeedback{" +
                "id=" + id +
                ", feedback='" + feedback + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
