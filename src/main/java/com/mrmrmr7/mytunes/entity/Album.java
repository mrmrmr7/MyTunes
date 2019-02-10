package com.mrmrmr7.mytunes.entity;

public class Album implements DBInstance {
    private long id;
    private String description;
    private long price;
    private int author_id;
    private int genre_id;

    public Album(long id, String description, long price, int author_id, int genre_id) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.author_id = author_id;
        this.genre_id = genre_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public int getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(int genre_id) {
        this.genre_id = genre_id;
    }
}
