package com.mrmrmr7.mytunes.entity;

import com.mrmrmr7.mytunes.dao.Identified;
import org.jetbrains.annotations.Contract;

import java.util.Objects;

public class Role implements Identified<Byte> {
    public Byte id;
    public String role;

    public Role(String role) {
        this.role = role.toLowerCase();
    }

    public Role(Byte id, String role) {
        this.id = id;
        this.role = role.toLowerCase();
    }

    @Override
    public Byte getId() {
        return id;
    }

    public void setId(Byte id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role.toLowerCase();
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role='" + role + '\'' +
                '}';
    }

    @Contract(value = "null -> false", pure = true)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role1 = (Role) o;
        return id == role1.id &&
                Objects.equals(role, role1.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role);
    }
}
