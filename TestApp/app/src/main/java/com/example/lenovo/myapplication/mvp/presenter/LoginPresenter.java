package com.example.lenovo.myapplication.mvp.presenter;

import android.content.Intent;
import android.text.TextUtils;

import com.example.lenovo.myapplication.glide.MainActivity;
import com.example.lenovo.myapplication.mvp.model.User;
import com.example.lenovo.myapplication.mvp.model.UserRepository;
import com.example.lenovo.myapplication.mvp.model.Userdao;
import com.example.lenovo.myapplication.mvp.ui.IView;

import java.util.List;

import androidx.lifecycle.LiveData;

public class LoginPresenter {
    private final IView.ILogin loginView;
    private boolean isLogIn; // TODO :this is required for what purpose?
    private final UserRepository userRepo;


    public LoginPresenter(IView.ILogin loginView, boolean isLogIn, UserRepository userRepo) {
        this.loginView = loginView;
        this.isLogIn = isLogIn;
        this.userRepo = userRepo;
    }

    public void validateUserOnLoginBtn(String email, String pwd){
        User checkUser = new User(email, pwd);
        if(isValidEmail(email) && isValidPwd(pwd)){
            LiveData<List<User>> userList = userRepo.getAllUsers();

            //TODO : how to search in existing user list
            boolean isUserExisting = userRepo.searchUserMailId(email);
            if(isUserExisting)
                loginView.onLoginSuccess(checkUser);
            else
                loginView.onLoginFailure("user not found : " + email , checkUser);
        }else
            loginView.onLoginFailure("invalid email or password : " + email, checkUser);
    }

    private boolean isValidPwd(String pwd) {
        return pwd.length() > 5;
    }

    private boolean isValidEmail(String email) {
        return email.contains("@");
    }

    public void createUser(User user){
        if(isValidEmail(user.getMailId()) && isValidPwd(user.getPwd())){
            userRepo.insert(user);
        }

    }


    public void validateFieldLength(String email, String pwd) {
        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(pwd))
            loginView.onDisableLogin(new User(email,pwd));
        else if(isValidEmail(email) && isValidPwd(pwd))
            loginView.onEnableLogin(new User(email,pwd));
        loginView.onDisableLogin(new User(email,pwd));
    }
}
