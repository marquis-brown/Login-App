package edu.citytech.Login.Controller;

import edu.citytech.Login.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    LoginService service;

    @PostMapping("/login")
    Map<String,Object> isValidUser(@RequestBody Map<String,Object> map) {

        String userId = map.get("username").toString();
        String password = map.get("password").toString();

        Map<String,Object> mapStatus = new HashMap<>();
        boolean status = service.getUserInfo(userId, password);
        mapStatus.put("status", status);

        return mapStatus;
    }
}
