package edu.citytech.Login.Repository;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class HomePageRepository {

    final private static String mysqlUrl = "jdbc:mysql://localhost:3306/loginusers";
    private String SQLHome = "SELECT USERNAME, EMAIL FROM loginusers.users WHERE USERNAME =";

    public Map<String, String> getHomePageInfo(String userName) {
        Map<String, String> userHomeInfo = new HashMap<>();
        SQLHome += "'" + userName + "'";
        try (Connection conn = DriverManager.getConnection(mysqlUrl, "root", "Terae9209$");
             Statement stment = conn.createStatement();
             ResultSet rs = stment.executeQuery(SQLHome);
        ){
            while(rs.next()) {
                userHomeInfo.put("username", rs.getString("USERNAME"));
                userHomeInfo.put("email", rs.getString("EMAIL"));
            }

        }catch(SQLException sx){
            System.out.println("SQL Exception");
            sx.printStackTrace();
        }
        return userHomeInfo;
    }
}
