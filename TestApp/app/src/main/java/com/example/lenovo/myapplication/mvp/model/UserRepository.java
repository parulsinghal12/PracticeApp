package com.example.lenovo.myapplication.mvp.model;


import android.app.Application;
import android.util.Log;

import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.lifecycle.LiveData;

public class UserRepository {

    private Userdao userDao;
    //private LiveData<List<User>> mAllUsers;

    public UserRepository(Application application) {
        UserDatabase db = UserDatabase.getDatabase(application);
        userDao = db.getUserDAO();
        //mAllUsers = userDao.getAllUsers();
    }

    public List<User> getAllUsers() {
        //mAllUsers = (List<User>) new getUsersAsyncTask(userDao).execute();
        try {
            return new getAllUsersTask(userDao).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static class getAllUsersTask extends android.os.AsyncTask<Void, Void, List<User>> {

        private Userdao mAsyncTaskDao;

        getAllUsersTask(Userdao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected List<User> doInBackground(Void... voids) {
            return mAsyncTaskDao.getAllUsers();
        }
    }

    public void insert (User newUser) {
        new insertAsyncTask(userDao).execute(newUser);
    }

    private static class insertAsyncTask extends android.os.AsyncTask<User, Void, Void> {

        private Userdao mAsyncTaskDao;

        insertAsyncTask(Userdao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    public boolean searchUserMailId (String email) {
        User foundUser = null;

        try {
            foundUser = new searchUserMailIdAsyncTask(userDao).execute(email).get();
        } catch (InterruptedException e) {
            Log.d("searchMovieId ","failed InterruptedException");
            e.printStackTrace();
        } catch (ExecutionException e) {
            Log.d("searchMovieId ","failed ExecutionException");
            e.printStackTrace();
        }
        return foundUser != null;
    }

    private static class searchUserMailIdAsyncTask extends android.os.AsyncTask<String, Void, User> {

        private Userdao mAsyncTaskDao;

        searchUserMailIdAsyncTask(Userdao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected User doInBackground(final String... params) {
            return mAsyncTaskDao.searchIfUserExists(params[0]);
        }

    }


}

