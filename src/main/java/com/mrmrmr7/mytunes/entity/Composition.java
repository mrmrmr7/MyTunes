package com.mrmrmr7.mytunes.entity;

public class Composition implements DBInstance {
    private long ID;
    private long price;
    private String name;
    private int album_id;

    public Composition(long ID, long price, String name, int album_id) {
        this.ID = ID;
        this.price = price;
        this.name = name;
        this.album_id = album_id;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
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
}
