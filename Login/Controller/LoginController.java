package edu.citytech.Login.Controller;

import com.jbbwebsolutions.http.utility.URLUtility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController {

    @FXML
    private TextField userTextField;

    @FXML
    private PasswordField pwdField;

    @FXML
    private Text failedLogin;

    @FXML
    void logIn(ActionEvent event){
        String sURL = "http://localhost:9209/login";
        Map<String, String> account = new HashMap<String, String>();

        account.put("username", userTextField.getText());
        account.put("password", pwdField.getText());

        Map<String, Object> status = URLUtility.post(sURL, account, 3);
        //if the user is verified
        if(status.get("status").equals(true)) {
            openHome(event);
        } else
            failedLogin.setText("Incorrect username and/or password");

        //userTextField.clear();
        //pwdField.clear();
    }

    //open application window
    @FXML
    private void openHome(ActionEvent event) {
        Parent root;
        //Singleton Class
        LoginManager login = LoginManager.getInstance();
        login.setUser(userTextField.getText());
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass()
                    .getClassLoader().getResource("edu/citytech/HomePage/View/HomePage.fxml")));
            Stage stage = new Stage();
            stage.setTitle("Welcome " + userTextField.getText());
            stage.setScene(new Scene(root));
            stage.show();

            // Hide this current window (if this is what you want)
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendData(ActionEvent event) {

    }

    @FXML
    void signUp(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("edu/citytech/SignUp/View/SignUp.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Sign Up Form");
            stage.setScene(new Scene(root));
            stage.show();
            // Hide this current window (if this is what you want)
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
