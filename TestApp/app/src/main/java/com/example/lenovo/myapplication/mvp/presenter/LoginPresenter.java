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
    private boolean isLogIn;
    private final UserRepository userRepo;


    public LoginPresenter(IView.ILogin loginView, boolean isLogIn, UserRepository userRepo) {
        this.loginView = loginView;
        this.isLogIn = isLogIn;
        this.userRepo = userRepo;
    }

    public void validateUserOnLoginBtn(User checkUser){
      //  User checkUser = new User(email, pwd);
        if(isValidEmail(checkUser.getMailId()) && isValidPwd(checkUser.getPwd())){
            List<User> userList = userRepo.getAllUsers();

            boolean isUserExisting = userRepo.searchUserMailId(checkUser.getMailId());
            if(isUserExisting)
                loginView.onLoginSuccess(checkUser);
            else
                loginView.onLoginFailure("user not found : " + checkUser.getMailId());
        }else
            loginView.onLoginFailure("invalid email or password : " + checkUser.getMailId());
    }

    boolean isValidPwd(String pwd) {
        return pwd.length() > 5;
    }

    boolean isValidEmail(String email) {
        return email.contains("@");
    }

    public void createUser(User user){
        if(isValidEmail(user.getMailId()) && isValidPwd(user.getPwd())){
            boolean isUserExisting = userRepo.searchUserMailId(user.getMailId());
            if(isUserExisting)
                loginView.onRegisterFailure("User Already Exists!!! Please Try Login");
            else
                loginView.onRegisterSuccess(user, user.getMailId() + " registered and trying to login...");
            userRepo.insert(user);
        }else{
            loginView.onRegisterFailure("Invalid User Name or Pwd entered. Username should contain @");

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
