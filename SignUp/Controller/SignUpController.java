package edu.citytech.SignUp.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class SignUpController {

    @FXML
    private TextField userNameField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField pwdField;

    @FXML
    private PasswordField confirmPwdField;

    @FXML
    private Text userNameMsg;

    @FXML
    private Text emailMsg;

    @FXML
    private Text passwordMsg;

    final private static String mysqlUrl = "jdbc:mysql://localhost:3306/loginusers";

    @FXML
    void createAccount(ActionEvent event) {

        boolean isUserNameValid = checkUserName();
        boolean isPwdValid = confirmPwd();
        boolean isEmailValid = checkEmail();

        if(!isUserNameValid)
            userNameMsg.setText("USERNAME ALREADY IN USE");
        if(!isEmailValid)
            emailMsg.setText("EMAIL IS INVALID");
        if(!isPwdValid)
            passwordMsg.setText("PASSWORDS DO NOT MATCH");
        if(isUserNameValid && isPwdValid && isEmailValid) {
            addToDatabase(userNameField.getText(), emailField.getText(), pwdField.getText());
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getClassLoader().getResource("edu/citytech/Login/View/LoginView.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Login Screen: Marquis Brown");
                stage.setScene(new Scene(root));
                stage.show();
                // Hide this current window (if this is what you want)
                ((Node) (event.getSource())).getScene().getWindow().hide();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void addToDatabase(String userName, String email, String password) {
        String SQL = "INSERT INTO loginusers.users (USERNAME, PASSWORD, EMAIL) "
                    + " VALUES (?,?,?)";
        try (Connection conn = DriverManager.getConnection(mysqlUrl, "root", "Terae9209$");
             PreparedStatement pst = conn.prepareStatement(SQL)
        ){
            pst.setString(1, userNameField.getText());
            pst.setString(2, pwdField.getText());
            pst.setString(3, emailField.getText());
            pst.execute();

        } catch(SQLException ex){
            System.out.println("SQL Exception");
            ex.printStackTrace();
        }
    }

    private boolean checkUserName() {
        boolean available = true;
        String SQL2 = "SELECT USERNAME FROM loginusers.users ";
        try (Connection conn = DriverManager.getConnection(mysqlUrl, "root", "Terae9209$");
             Statement stment = conn.createStatement();
             ResultSet rs = stment.executeQuery(SQL2);
        ){
            while(rs.next()) {
                if (rs.getString("USERNAME").equals(userNameField.getText()))
                    available = false;
            }
        } catch(SQLException ex){
            System.out.println("SQL Exception");
            ex.printStackTrace();
        }
        return available;
    }

    private boolean confirmPwd() {
        return pwdField.getText().equals(confirmPwdField.getText());
    }

    private boolean checkEmail() {
        String email = emailField.getText();

        //check if email address already exists in database

        return email.contains("@") && email.contains(".com");
    }
}