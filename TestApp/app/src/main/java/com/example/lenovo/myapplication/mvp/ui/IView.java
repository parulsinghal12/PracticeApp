package com.example.lenovo.myapplication.mvp.ui;

import com.example.lenovo.myapplication.mvp.model.User;

public interface IView {

    interface ILogin{
        void onLoginSuccess(User user); // passed user exists in DB
        void onLoginFailure(String errorMsg, User user);
        void onEnableLogin(User user);
        void onDisableLogin(User user);

        //
        /*
        * onPwdValidationSuccess - if pwd entered is on specified lenth or caps etc
        * onPwdValidationFailure
        * onEmailValidationSuccess etc..
        * */
    }
}
