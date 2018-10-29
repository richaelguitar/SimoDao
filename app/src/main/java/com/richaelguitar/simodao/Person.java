package com.richaelguitar.simodao;

import com.richaelguitar.dao.annotation.Column;
import com.richaelguitar.dao.annotation.Table;
import com.richaelguitar.dao.entity.DaoEntity;

@Table(name = "tb_person",indexColumns = {"p_name","p_sex"})
public class Person extends DaoEntity {

    @Column(name="p_name")
    private String name;
    @Column(name="p_age")
    private int age;
    @Column(name="p_sex")
    private int sex;

    public Person(String name, int age, int sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }
}
