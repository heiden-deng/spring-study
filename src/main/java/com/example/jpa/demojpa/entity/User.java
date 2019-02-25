package com.example.jpa.demojpa.entity;

import com.example.jpa.demojpa.converter.SexConverter;
import com.example.jpa.demojpa.enumeration.SexEnum;

import javax.persistence.*;

@Entity(name="user")
@Table(name = "t_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @Column(name="user_name",nullable = false)
    private String userName;

    @Column(name = "note")
    private String note;


    @Convert(converter= SexConverter.class)
    private SexEnum sex = null;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public SexEnum getSex() {
        return sex;
    }

    public void setSex(SexEnum sex) {
        this.sex = sex;
    }
}
