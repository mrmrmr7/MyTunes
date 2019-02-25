package com.mrmrmr7.mytunes.entity;

import com.mrmrmr7.mytunes.dao.Identified;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class UserAlbum implements Identified<Integer> {
    Integer id;
    List<Integer> cortageIdList;
    List<Integer> albumIdList;

    public UserAlbum(Integer id, List<Integer> cortageIdList, List<Integer> albumIdList) {
        this.id = id;
        this.cortageIdList = cortageIdList;
        this.albumIdList = albumIdList;
    }

    public UserAlbum(Integer cortageId, Integer userId, Integer albumId) {
        this(userId, new ArrayList<>(Arrays.asList(cortageId)), new ArrayList<>(Arrays.asList(albumId)));
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Integer> getCortageIdList() {
        return cortageIdList;
    }

    public void setCortageIdList(List<Integer> cortageIdList) {
        this.cortageIdList = cortageIdList;
    }

    public List<Integer> getAlbumIdList() {
        return albumIdList;
    }

    public Integer getCortageId(int id) {
        return cortageIdList.get(id);
    }

    public Integer getAlbumId(int id) {
        return albumIdList.get(id);
    }

    public void setAlbumIdList(List<Integer> albumIdList) {
        this.albumIdList = albumIdList;
    }

    @Override
    public String toString() {
        return "UserAlbum{" +
                "id=" + id +
                ", cortageIdList=" + cortageIdList +
                ", albumIdList=" + albumIdList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAlbum userAlbum = (UserAlbum) o;
        return Objects.equals(id, userAlbum.id) &&
                Objects.equals(cortageIdList, userAlbum.cortageIdList) &&
                Objects.equals(albumIdList, userAlbum.albumIdList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cortageIdList, albumIdList);
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    public void addCompositionId(Integer id, Integer compositionId) {
        this.cortageIdList.add(id);
        this.albumIdList.add(compositionId);
    }
}
