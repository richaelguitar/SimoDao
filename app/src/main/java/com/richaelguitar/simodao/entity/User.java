package com.richaelguitar.simodao.entity;

import com.richaelguitar.dao.annotation.Column;
import com.richaelguitar.dao.annotation.Table;
import com.richaelguitar.dao.entity.DaoEntity;

@Table(name = "tb_user")
public class User extends DaoEntity {


    public User(String userName, String password, String origin) {
        this.userName = userName;
        this.password = password;
        this.origin = origin;
    }


    @Column(name = "user_name")
    public String userName;
    @Column(name="pass_word")
    public String password;
    @Column(name = "origin")
    public String origin;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }
}
