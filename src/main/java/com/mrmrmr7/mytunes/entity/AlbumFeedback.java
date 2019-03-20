package com.mrmrmr7.mytunes.entity;

import com.mrmrmr7.mytunes.dao.Identified;

import java.sql.Date;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Objects;

public class AlbumFeedback implements Identified<Integer> {
    private Integer id;
    private String feedback;
    private Timestamp timestamp;

    public AlbumFeedback() {}

    public AlbumFeedback(Integer id, String feedback, Timestamp timestamp) {
        this.id = id;
        this.feedback = feedback;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "AlbumFeedback{" +
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlbumFeedback that = (AlbumFeedback) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(feedback, that.feedback);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, feedback, timestamp);
    }
}
