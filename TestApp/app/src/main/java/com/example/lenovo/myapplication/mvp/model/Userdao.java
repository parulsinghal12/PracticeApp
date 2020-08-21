package com.example.lenovo.myapplication.mvp.model;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface Userdao {

    @Query("SELECT * FROM user")
    List<User> getAllUsers();

    @Insert
    void insert(User newUser);

    @Query("SELECT * FROM user WHERE MAIL =:mail_id LIMIT 1")
    User searchIfUserExists(String mail_id);
}
