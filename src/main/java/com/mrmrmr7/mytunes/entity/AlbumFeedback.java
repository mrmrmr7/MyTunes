package com.mrmrmr7.mytunes.entity;

import com.mrmrmr7.mytunes.dao.Identified;

import java.sql.Date;
import java.sql.Date;
import java.util.Calendar;
import java.util.Objects;

public class AlbumFeedback implements Identified<Integer> {
    private Integer id;
    private String feedback;
    private Date date;

    public AlbumFeedback(Integer id, String feedback) {
        this.id = id;
        this.feedback = feedback;
        this.date = new Date(Calendar.getInstance().getTime().getTime());
    }

    @Override
    public String toString() {
        return "AlbumFeedback{" +
                "id=" + id +
                ", feedback='" + feedback + '\'' +
                ", date=" + date +
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
        return Objects.hash(id, feedback, date);
    }
}
