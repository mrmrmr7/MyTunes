package com.mrmrmr7.mytunes.entity;

import com.mrmrmr7.mytunes.dao.Identified;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class UserBonus implements Identified<Integer> {
    Integer id;
    private List<Integer> cortageIdList = new ArrayList<>();
    private List<Integer> bonusIdList = new ArrayList<>();

    public UserBonus(Integer userId, List<Integer> cortageIdList, List<Integer> bonusIdList) {
        this.id = userId;
        this.cortageIdList = cortageIdList;
        this.bonusIdList = bonusIdList;
    }

    public UserBonus(Integer cortageId, Integer userId, Integer bonusId) {
        this(userId, new ArrayList<>(Arrays.asList(cortageId)), new ArrayList<>(Arrays.asList(bonusId)));
    }

    public Integer getCortageId(int i) {
        return cortageIdList.get(i);
    }

    public List<Integer> getBonusIdList() {
        return bonusIdList;
    }

    public int getBonusId(Integer bonusId) {
        return bonusIdList.get(bonusId);
    }

    public void addBonusId(int cortageId, int bonusId) {
        cortageIdList.add(cortageId);
        bonusIdList.add(bonusId);
    }

    @Override
    public String toString() {
        return "UserBonus{" +
                "id=" + id +
                ", cortageIdList=" + cortageIdList.toString() +
                ", bonusIdList=" + bonusIdList.toString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserBonus userBonus = (UserBonus) o;
        return Objects.equals(id, userBonus.id) &&
                Objects.equals(bonusIdList, userBonus.bonusIdList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bonusIdList);
    }

    @Override
    public Integer getId() {
        return id;
    }
}
