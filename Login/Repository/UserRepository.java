package edu.citytech.Login.Repository;

import java.sql.*;
import java.util.Map;

public class UserRepository {

    final static String mysqlUrl = "jdbc:mysql://localhost:3306/loginusers";
    final static String SQL = "SELECT USERNAME, PASSWORD "
                            + "FROM loginusers.users ";

    public boolean isAuthorized(String userId, String password) {
        boolean status = false;

        try (Connection conn = DriverManager.getConnection(mysqlUrl, "root", "Terae9209$");
             Statement stment = conn.createStatement();
             ResultSet rs = stment.executeQuery(SQL);
        ){
            while(rs.next()) {
                String dbUsername = rs.getString("USERNAME");
                String dbPassword = rs.getString("PASSWORD");

                 if(password.equals(dbPassword) && userId.equals(dbUsername))
                     status = true;
            }

        } catch (SQLException ex) {
            System.out.println("SQL Exception");
            ex.printStackTrace();
        }

        return status;
    }

}