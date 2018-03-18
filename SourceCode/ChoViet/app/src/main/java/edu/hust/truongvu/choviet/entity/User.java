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
    private Date birthday;
    private String phone;
    private int gender;
    private int avatar;
    private int idShop;
    private int idTypeUser;
    private int typeLogin;

    public User(){

    }

    public User(int id, String fullname, String username, String password,
                String address, Date birthday, String phone, int gender,
                int avatar, int idShop, int idTypeUser, int typeLogin) {
        this.id = id;
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.address = address;
        this.birthday = birthday;
        this.phone = phone;
        this.gender = gender;
        this.avatar = avatar;
        this.idShop = idShop;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
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

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public int getIdShop() {
        return idShop;
    }

    public void setIdShop(int idShop) {
        this.idShop = idShop;
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
