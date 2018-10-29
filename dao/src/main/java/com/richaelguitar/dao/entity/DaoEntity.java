package com.richaelguitar.dao.entity;

import com.richaelguitar.dao.annotation.Column;

public class DaoEntity {

    @Column(name="_ID",isPrimaryKey = true)
    public Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
