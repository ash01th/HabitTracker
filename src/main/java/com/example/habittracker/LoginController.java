package com.example.habittracker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.Event;

import java.security.PublicKey;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginController {
    @FXML
    private TextField UserNameTextField;
    @FXML
    private PasswordField PasswordPasswordField;
    @FXML
    private Button LoginButton;
    @FXML
    private Button ExitButton;
    @FXML
    private Label LoginLabel;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void  ExitButtonAction(ActionEvent e){
        Stage stage=(Stage)ExitButton.getScene().getWindow();
        stage.close();
    }
    public void  LoginButtonAction(ActionEvent e)throws Exception{
        if (UserNameTextField.getText().isBlank()==false&& PasswordPasswordField.getText().isBlank()==false) {
            ValidateLogin();
            if(ValidateLogin()){
                Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
                stage = (Stage)((Node)e.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            }
        }
        else{
            LoginLabel.setText("Please Enter the Username and Password");
        }
    }
    public boolean ValidateLogin(){
        boolean flag=false;
        DataBaseConnection connectNow =new DataBaseConnection();
        Connection connectDB=connectNow.getDBConnection();
        String VerifyLogin="SELECT count(1) FROM users  WHERE userName='"+UserNameTextField.getText()+"'and password='"+PasswordPasswordField.getText()+"';";
        try{
            Statement statement=connectDB.createStatement();
            ResultSet queryResult=statement.executeQuery(VerifyLogin);
            while(queryResult.next()){
                if (queryResult.getInt(1)==1) {
                    LoginLabel.setText("Login Successful");
                    flag=true;

                }
                else {
                    LoginLabel.setText("Wrong Credentials");
                    flag=false;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;


    }
}
