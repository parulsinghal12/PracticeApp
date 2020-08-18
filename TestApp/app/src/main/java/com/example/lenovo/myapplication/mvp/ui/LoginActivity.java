package com.example.lenovo.myapplication.mvp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.databinding.ActivityLoginBinding;
import com.example.lenovo.myapplication.glide.MainActivity;
import com.example.lenovo.myapplication.mvp.model.User;
import com.example.lenovo.myapplication.mvp.model.UserDatabase;
import com.example.lenovo.myapplication.mvp.model.UserRepository;
import com.example.lenovo.myapplication.mvp.model.Userdao;
import com.example.lenovo.myapplication.mvp.presenter.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements IView.ILogin{

    ActivityLoginBinding binding;

    UserRepository userDao;
    private LoginPresenter logInPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        //TODO : confirm
        userDao = new UserRepository(getApplication());
        logInPresenter = new LoginPresenter(this, true, userDao);

        binding.username.addTextChangedListener(textWatcher);
        binding.password.addTextChangedListener(textWatcher);

    }

    @Override
    public void onLoginSuccess(User user) {
        Toast.makeText(this, "Login successful for user", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void onLoginFailure(String errorMsg, User user) {
        Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show();
        logInPresenter.createUser(user );
    }

    @Override
    public void onEnableLogin(User user) {
        binding.login.setEnabled(true);
    }

    @Override
    public void onDisableLogin(User user) {
        binding.login.setEnabled(false);
    }

    public void onLoginBtnClick(View view){
        String email = String.valueOf(binding.username.getText());
        String pwd = String.valueOf(binding.password.getText());
        logInPresenter.validateUserOnLoginBtn(email, pwd);
        /*if(isLogin){
            logInPresenter.isValidUser(name_et.getText().toString(), password_et.getText().toString());
        }
        else{
            logInPresenter.createUser(name_et.getText().toString(), password_et.getText().toString(),email_et.getText().toString(),phoneNum_et.getText().toString());
        }*/
    }

    private TextWatcher textWatcher = new TextWatcher(){

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            //binding.login.setEnabled(!TextUtils.isEmpty(binding.username.getText().toString()) || !TextUtils.isEmpty(binding.username.getText().toString()));
            String email = binding.username.getText().toString().trim();
            String pwd = binding.password.getText().toString().trim();
            //logInPresenter.validateFieldLength(email, pwd);
            //TODO : presenter logic not working here
            binding.login.setEnabled(!email.isEmpty() && !pwd.isEmpty());
        }
    };
}