package edu.citytech.Login.Service;

import edu.citytech.Login.Repository.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class LoginService {

    UserRepository userRepository = new UserRepository();

    public LoginService() {}

    public boolean getUserInfo(String userId, String password) {
        return userRepository.isAuthorized(userId, password);
    }

}
