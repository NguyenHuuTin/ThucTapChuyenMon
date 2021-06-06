package com.nguyenhuutin.model;

import java.io.Serializable;

public class Users implements Serializable {
    public String SDT;
    public String Email;
    public String UserName;
    public String Password;
    public int Permission;

    public Users() {
    }

    public Users(String SDT, String email, String userName, String password, int permission) {
        this.SDT = SDT;
        Email = email;
        UserName = userName;
        Password = password;
        Permission = permission;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getPermission() {
        return Permission;
    }

    public void setPermission(int permission) {
        Permission = permission;
    }
}
