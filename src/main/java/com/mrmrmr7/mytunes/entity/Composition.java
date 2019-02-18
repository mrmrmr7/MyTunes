package com.mrmrmr7.mytunes.entity;

import com.mrmrmr7.mytunes.dao.Identified;

public class Composition  implements Identified<Integer> {
    private int id;
    private int year;
    private int price;
    private int album_id;
    private String name;

    public Composition(int id, int price, String name, int album_id, int year) {
        this.year = year;
        this.id = id;
        this.price = price;
        this.name = name;
        this.album_id = album_id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(int album_id) {
        this.album_id = album_id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Composition{" +
                "id=" + id +
                ", year=" + year +
                ", price=" + price +
                ", album_id=" + album_id +
                ", name='" + name + '\'' +
                '}';
    }
}
