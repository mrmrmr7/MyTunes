package com.mrmrmr7.mytunes.entity;

import com.mrmrmr7.mytunes.dao.Identified;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class UserMusicSelection implements Identified<Integer> {
    Integer userId;
    List<Integer> cortageIdList;
    List<Integer> musicSelectionIdList;

    public UserMusicSelection(Integer userId, List<Integer> cortageIdList, List<Integer> musicSelectionIdList) {
        this.userId = userId;
        this.cortageIdList = cortageIdList;
        this.musicSelectionIdList = musicSelectionIdList;
    }

    public UserMusicSelection(Integer cortageId, Integer userId, Integer musicSelectionId) {
        this(userId, new ArrayList<>(Arrays.asList(cortageId)), new ArrayList<>(Arrays.asList(musicSelectionId)));
    }

    public UserMusicSelection(Integer userId, Integer musicSelectionId) {
        this(0, userId, musicSelectionId);
    }

    public void addMusicSelection(Integer cortageId, Integer musicSelection) {
        cortageIdList.add(cortageId);
        musicSelectionIdList.add(musicSelection);
    }


    public Integer getCortageId(Integer id) {
        return cortageIdList.get(id);
    }

    public Integer getSelectionId(Integer id) {
        return musicSelectionIdList.get(id);
    }

    public void setId(Integer id) {
        this.userId = id;
    }

    public List<Integer> getCortageIdList() {
        return cortageIdList;
    }

    public void setCortageIdList(List<Integer> cortageIdList) {
        this.cortageIdList = cortageIdList;
    }

    public List<Integer> getMusicSelectionIdList() {
        return musicSelectionIdList;
    }

    public void setMusicSelectionIdList(List<Integer> musicSelectionIdList) {
        this.musicSelectionIdList = musicSelectionIdList;
    }

    @Override
    public String toString() {
        return "UserMusicSelection{" +
                "id=" + userId +
                ", cortageIdList=" + cortageIdList +
                ", musicSelectionIdList=" + musicSelectionIdList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserMusicSelection that = (UserMusicSelection) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(cortageIdList, that.cortageIdList) &&
                Objects.equals(musicSelectionIdList, that.musicSelectionIdList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, cortageIdList, musicSelectionIdList);
    }

    @Override
    public Integer getId() {
        return this.userId;
    }

    public Integer getMusicSelectionId(int i) {
        return musicSelectionIdList.get(i);
    }
}
