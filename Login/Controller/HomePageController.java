package edu.citytech.Login.Controller;

import edu.citytech.Login.Service.HomePageService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class HomePageController {

    @GetMapping("/home")
    Map<String,String> getHomePageInfo(@RequestParam String user) {
        HomePageService service = new HomePageService();
        Map<String, String> homeInfo = service.getHomeInfo(user);

        return homeInfo;
    }
}