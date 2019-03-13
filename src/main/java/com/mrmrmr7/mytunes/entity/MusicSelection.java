package com.mrmrmr7.mytunes.entity;

import com.mrmrmr7.mytunes.dao.Identified;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MusicSelection implements Identified<Integer> {
    private List<Integer> cortageIdList;
    private List<Integer> compositionIdList;
    private long price;
    private Integer selection_id;

    public MusicSelection(List<Integer> cortageIdList, List<Integer> composition_idList, Integer selection_id) {
        this.cortageIdList = cortageIdList;
        this.compositionIdList = composition_idList;
        this.selection_id = selection_id;
    }

    public MusicSelection(Integer id, Integer selection_id, Integer compositionIdList) {
        this(new ArrayList<>(Arrays.asList(id)), new ArrayList<>(Arrays.asList(compositionIdList)), selection_id);
    }

    public int getCortageId(int id) {
        return cortageIdList.get(id);
    }

    public void addCompositionId(Integer id, Integer compositionIdList) {
        this.cortageIdList.add(id);
        this.compositionIdList.add(compositionIdList);
    }

    @Override
    public Integer getId() {
        return selection_id;
    }

    public List<Integer> getCompositionIdList() {
        return compositionIdList;
    }

    public Integer getCompositionId(int id) {
        return compositionIdList.get(id);
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "MusicSelection{" +
                "selection_id=" + selection_id +
                ", id=" + cortageIdList.toString() +
                ", compositionIdList=" + compositionIdList.toString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MusicSelection that = (MusicSelection) o;
        return selection_id == that.selection_id &&
                Objects.equals(compositionIdList, that.compositionIdList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(compositionIdList, selection_id);
    }
}
