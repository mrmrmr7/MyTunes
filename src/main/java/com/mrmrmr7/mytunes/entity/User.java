package com.mrmrmr7.mytunes.entity;

import com.mrmrmr7.mytunes.dao.Identified;

import java.sql.Date;

public class User implements Identified<Integer> {
    private int id;
    private Date registerData;
    private String login;
    private String password;
    private String firstName;
    private String secondName;
    private String email;
    private long balance;
    private byte sale;
    private byte roleId;
    private byte statusId;

    public User(int ID, Date registerData,
                String LOGIN, String password,
                String firstName, String secondName,
                String email,
                long balance, byte sale,
                byte roleId, byte statusId) {
        this.id = ID;
        this.login = LOGIN;
        this.registerData = registerData;
        this.password = password;
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.balance = balance;
        this.sale = sale;
        this.roleId = roleId;
        this.statusId = statusId;
    }

    public User(String LOGIN, String password,
                String firstName, String secondName,
                String email,
                long balance, byte sale,
                byte roleId, byte statusId) {
        this(0, new Date(21213135435L), LOGIN, password, firstName, secondName, email, balance, sale, roleId, statusId);
    }

    public String getLogin() {
        return login;
    }

    public Date getRegisterData() {
        return registerData;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
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

    public byte getRoleId() {
        return roleId;
    }

    public void setRoleId(byte roleId) {
        this.roleId = roleId;
    }

    public byte getStatusId() {
        return statusId;
    }

    public void setStatusId(byte statusId) {
        this.statusId = statusId;
    }



    @Override
    public String toString() {
        return "User{" +
                "ID=" + id +
                ", LOGIN='" + login + '\'' +
                ", register_data=" + registerData +
                ", password='" + password + '\'' +
                ", first_name='" + firstName + '\'' +
                ", second_name='" + secondName + '\'' +
                ", email='" + email + '\'' +
                ", balance=" + balance +
                ", sale=" + sale +
                ", role_id=" + roleId +
                ", status_id=" + statusId +
                '}';
    }

    @Override
    public Integer getId() {
        return id;
    }
}
