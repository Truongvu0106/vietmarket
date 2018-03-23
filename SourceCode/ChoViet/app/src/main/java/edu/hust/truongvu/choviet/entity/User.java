package edu.hust.truongvu.choviet.entity;

import java.util.Date;

/**
 * Created by truon on 3/15/2018.
 */

public class User {
    private int id;
    private String fullname;
    private String username;
    private String password;
    private String address;
    private String birthday;
    private String phone;
    private int gender;
    private String avatar;
    private int idTypeUser;
    private int typeLogin;

    public User(){

    }

    public User(int id, String fullname, String username, String password,
                String address, String birthday, String phone, int gender,
                String avatar, int idTypeUser, int typeLogin) {
        this.id = id;
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.address = address;
        this.birthday = birthday;
        this.phone = phone;
        this.gender = gender;
        this.avatar = avatar;
        this.idTypeUser = idTypeUser;
        this.typeLogin = typeLogin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getIdTypeUser() {
        return idTypeUser;
    }

    public void setIdTypeUser(int idTypeUser) {
        this.idTypeUser = idTypeUser;
    }

    public int getTypeLogin() {
        return typeLogin;
    }

    public void setTypeLogin(int typeLogin) {
        this.typeLogin = typeLogin;
    }
}
