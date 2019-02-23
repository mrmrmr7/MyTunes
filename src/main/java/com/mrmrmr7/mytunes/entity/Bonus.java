package com.mrmrmr7.mytunes.entity;

import com.mrmrmr7.mytunes.dao.Identified;

import java.util.Objects;

public class Bonus implements Identified<Integer> {
    private Integer id;
    private String bonus;

    public Bonus(String bonus) {
        this.bonus = bonus;
    }

    public Bonus(Integer id, String bonus) {
        this.id = id;
        this.bonus = bonus;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }

    @Override
    public String toString() {
        return "Bonus{" +
                "id=" + id +
                ", bonus='" + bonus + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bonus bonus1 = (Bonus) o;
        return Objects.equals(id, bonus1.id) &&
                Objects.equals(bonus, bonus1.bonus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bonus);
    }
}
