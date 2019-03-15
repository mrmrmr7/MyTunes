package com.mrmrmr7.mytunes.dto;

public class CompositionDto {
    private String name;
    private String album;
    private String author;
    private int price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public CompositionDto(String name, String album, String author, int price) {
        this.name = name;
        this.album = album;
        this.author = author;
        this.price = price;
    }

    public CompositionDto() {}
}
