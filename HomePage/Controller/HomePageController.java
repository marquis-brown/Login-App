package edu.citytech.HomePage.Controller;

import com.jbbwebsolutions.http.utility.URLUtility;
import edu.citytech.Login.Controller.LoginManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {

    @FXML
    private ImageView profileIcon;

    @FXML
    private Text userName;

    @FXML
    private Text email;

    @FXML
    private Label urlText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String homeURL = "http://localhost:9209/home?user=";
        LoginManager login = LoginManager.getInstance();
        homeURL += login.getUser();

        Map<String, Object> homeInfo = URLUtility.get(homeURL, Map.class);
        String usernameTxt = homeInfo.get("username").toString();
        String emailTxt = homeInfo.get("email").toString();


        userName.setText(usernameTxt);
        email.setText(emailTxt);
    }

}