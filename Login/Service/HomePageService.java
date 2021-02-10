package edu.citytech.Login.Service;

import edu.citytech.Login.Repository.HomePageRepository;
import edu.citytech.Login.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class HomePageService {

    HomePageRepository hpr = new HomePageRepository();

    public HomePageService() {}

    public Map<String, String> getHomeInfo(String userId){
        return hpr.getHomePageInfo(userId);
    }
}
