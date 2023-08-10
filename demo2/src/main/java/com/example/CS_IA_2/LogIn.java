package com.example.CS_IA_2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.*;

public class LogIn {
    public LogIn() {

    }
    @FXML
    private Button button;
    @FXML
    private Label wrongLogin;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    IDandPasswords loginInfo = new IDandPasswords();
    HashMap<String,String> hashMap = loginInfo.getLoginInfo();

    private void checkLogin() throws IOException {
        Main m = new Main();
        String user = username.getText();
        String pass = password.getText();
        if(hashMap.containsKey(user) && hashMap.get(user).equals(pass)){
            wrongLogin.setText("Success!");
            m.close(button);
            m.changeScene("home.fxml");
        }
        else if(user.isEmpty() && pass.isEmpty()){
            wrongLogin.setText("Please enter your data");
        }
        else{
            wrongLogin.setText("Wrong username or password!");
        }

    }

    public void userLogin(ActionEvent actionEvent) throws IOException {
        checkLogin();
    }
}
