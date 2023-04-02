package com.example.habittracker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ClassSelectContoller {
    @FXML
    private Button UserButton;
    @FXML
    private Button LoginButton;
    private Stage stage;
    private Scene scene;
    private Parent root;
    public void UserButtonAction(ActionEvent e) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("UserLogin.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    public void AdminButtonAction(ActionEvent e) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
