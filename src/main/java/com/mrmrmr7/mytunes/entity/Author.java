package com.mrmrmr7.mytunes.entity;

import com.mrmrmr7.mytunes.dao.Identified;

public class Author implements Identified<Integer> {
    private int id;
    private String firstName;
    private String secondName;
    private String pseudonim;

    public Author(int id, String firstName, String secondName, String pseudonim) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.pseudonim = pseudonim;
    }

    public Author(String firstName, String secondName, String pseudonim) {
        this(0, firstName, secondName, pseudonim);
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getPseudonim() {
        return pseudonim;
    }

    public void setPseudonim(String pseudonim) {
        this.pseudonim = pseudonim;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", pseudonim='" + pseudonim + '\'' +
                '}';
    }
}
