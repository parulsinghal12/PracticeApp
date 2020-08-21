package com.example.lenovo.myapplication.mvp.presenter;

import com.example.lenovo.myapplication.mvp.model.User;
import com.example.lenovo.myapplication.mvp.model.UserRepository;
import com.example.lenovo.myapplication.mvp.ui.IView;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.OngoingStubbing;

import java.util.List;

import androidx.lifecycle.LiveData;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockitoSession;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoginPresenterTest {

    @Mock
    static UserRepository userRepo;

    @Mock
    IView.ILogin loginView ; //= mock(IView.ILogin.class);

    static List<User> userList;


    @Before
    public void setUp() throws Exception {
        //mockitoSession().initMocks()
        MockitoAnnotations.initMocks(this);
    }

    @BeforeClass
    public static void setdata(){

    }

    @Test
    public void testSuccessLogin() {

        User checkUser = getUserInput();
        LoginPresenter presenter = new LoginPresenter(loginView,true,userRepo);

        when(userRepo.searchUserMailId(checkUser.getMailId())).thenReturn(true);
        presenter.validateUserOnLoginBtn(checkUser);

        verify(loginView).onLoginSuccess(checkUser);

    }

    @Test
    public void testFailureLogin() {

        //IView.ILogin loginView = mock(IView.ILogin.class);
        // User checkUser = mock(User.class); // cant do as only Interfaces can be mocked.
        User checkUser = getUserFailedInput();

        //List<User> userList = userRepo.getAllUsers();


        LoginPresenter presenter = new LoginPresenter(loginView,true,userRepo);
        //when(presenter.isValidEmail(checkUser.getMailId())).thenReturn(false);
        //when(presenter.isValidPwd(checkUser.getPwd())).thenReturn(false);
        presenter.validateUserOnLoginBtn(checkUser);
        //when(userRepo.searchUserMailId(checkUser.getMailId())).thenReturn(false);

        //loginView.onLoginFailure("invalid email or password : " + email, checkUser);
        verify(loginView).onLoginFailure("invalid email or password : " + checkUser.getMailId() );

    }

    private User getUserInput() {
        return new User("test@gmail.com", "12345678");
    }

    private User getUserFailedInput() {
        return new User("test", "12345678");
    }
}