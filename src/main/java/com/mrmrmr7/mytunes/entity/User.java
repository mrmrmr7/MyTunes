package com.mrmrmr7.mytunes.entity;

import java.util.Date;

public class User implements DBInstance {
    private int id;
    private Date register_data;
    private String login;
    private String password;
    private String first_name;
    private String second_name;
    private String email;
    private long balance;

    public int getID() {
        return id;
    }

    public String getLOGIN() {
        return login;
    }

    public Date getRegister_data() {
        return register_data;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public byte getSale() {
        return sale;
    }

    public void setSale(byte sale) {
        this.sale = sale;
    }

    public byte getRole_id() {
        return role_id;
    }

    public void setRole_id(byte role_id) {
        this.role_id = role_id;
    }

    public byte getStatus_id() {
        return status_id;
    }

    public void setStatus_id(byte status_id) {
        this.status_id = status_id;
    }

    private byte sale;
    private byte role_id;
    private byte status_id;

    public User(int ID, Date register_data,
                String LOGIN, String password,
                String first_name, String second_name,
                String email,
                long balance, byte sale,
                byte role_id, byte status_id) {
        this.id = ID;
        this.login = LOGIN;
        this.register_data = register_data;
        this.password = password;
        this.first_name = first_name;
        this.second_name = second_name;
        this.email = email;
        this.balance = balance;
        this.sale = sale;
        this.role_id = role_id;
        this.status_id = status_id;
    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + id +
                ", LOGIN='" + login + '\'' +
                ", register_data=" + register_data +
                ", password='" + password + '\'' +
                ", first_name='" + first_name + '\'' +
                ", second_name='" + second_name + '\'' +
                ", email='" + email + '\'' +
                ", balance=" + balance +
                ", sale=" + sale +
                ", role_id=" + role_id +
                ", status_id=" + status_id +
                '}';
    }
}
