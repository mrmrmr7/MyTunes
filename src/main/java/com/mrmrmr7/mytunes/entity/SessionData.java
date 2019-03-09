package com.mrmrmr7.mytunes.entity;

import com.mrmrmr7.mytunes.dao.Identified;

import java.util.Base64;
import java.util.Objects;

public class SessionData implements Identified<Integer> {
    private int user_id;
    private byte[] session_hash;

    public SessionData(int user_id, byte[] session_hash) {
        this.user_id = user_id;
        this.session_hash = session_hash;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public byte[] getSession_hash() {
        return session_hash;
    }

    public void setSession_hash(byte[] session_hash) {
        this.session_hash = session_hash;
    }

    @Override
    public String toString() {
        return "SessionData{" +
                "user_id=" + user_id +
                ", session_hash='" + Base64.getDecoder().decode(session_hash) + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionData that = (SessionData) o;
        return user_id == that.user_id &&
                Objects.equals(session_hash, that.session_hash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, session_hash);
    }

    @Override
    public Integer getId() {
        return user_id;
    }
}
