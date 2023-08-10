package com.example.CS_IA_2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class Investment extends Home {
    @FXML
    private Button backButton;



    public void back(ActionEvent actionEvent) throws IOException {
        Main m = new Main();
        m.close(backButton);
        m.changeScene("home.fxml");

    }
}
