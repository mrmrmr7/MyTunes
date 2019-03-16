package com.mrmrmr7.mytunes.entity;

import com.mrmrmr7.mytunes.dao.Identified;

import java.util.Objects;

public class MusicSelectionInfo implements Identified<Integer> {
    private Integer id;
    private long price;
    private String name;
    private String description;

    public MusicSelectionInfo(){
    }

    public MusicSelectionInfo(int id, long price, String name, String description) {
        this.id = id;
        this.description = description;
        this.name = name;
        this.price = price;
    }

    public MusicSelectionInfo(String description, long price, String name) {
        this(0, price, name, description);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MusicSelectionInfo musicSelectionInfo = (MusicSelectionInfo) o;
        return price == musicSelectionInfo.price &&
                Objects.equals(id, musicSelectionInfo.id) &&
                Objects.equals(description, musicSelectionInfo.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, price);
    }

    @Override
    public String toString() {
        return "musicSelectionInfo{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
