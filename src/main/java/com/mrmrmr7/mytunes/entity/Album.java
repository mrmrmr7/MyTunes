package com.mrmrmr7.mytunes.entity;

import com.mrmrmr7.mytunes.dao.Identified;

import java.util.Objects;

public class Album implements Identified<Integer> {
    private Integer id;
    private String description;
    private long price;
    private int author_id;
    private int genre_id;

    public Album(int id, long price, String description, int author_id, int genre_id) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.author_id = author_id;
        this.genre_id = genre_id;
    }

    public Album(String description, long price, int author_id, int genre_id) {
        this(0, price, description, author_id, genre_id);
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return price == album.price &&
                author_id == album.author_id &&
                genre_id == album.genre_id &&
                Objects.equals(id, album.id) &&
                Objects.equals(description, album.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, price, author_id, genre_id);
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", author_id=" + author_id +
                ", genre_id=" + genre_id +
                '}';
    }
}
