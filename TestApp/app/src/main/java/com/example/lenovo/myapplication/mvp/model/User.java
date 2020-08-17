package com.example.lenovo.myapplication.mvp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class User {

    @PrimaryKey(autoGenerate = true)
    int id;

    @ColumnInfo(name = "MAIL")
    String mailId;

    @ColumnInfo(name = "PWD")
    String pwd;

    public User(String mailId, String pwd) {
        this.mailId = mailId;
        this.pwd = pwd;
    }


}
