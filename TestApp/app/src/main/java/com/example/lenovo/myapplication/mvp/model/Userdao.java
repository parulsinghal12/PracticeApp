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
    LiveData<List<User>> getAllUsers();

    @Update
    void insert(User newUser);
}
