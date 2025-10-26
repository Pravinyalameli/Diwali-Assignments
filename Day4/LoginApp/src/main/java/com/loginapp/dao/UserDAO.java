// File: UserDAO.java
package com.loginapp.dao;

import com.loginapp.model.User;
import java.sql.*;

public class UserDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/login_db?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "your_mysql_password"; // CHANGE THIS!
    
    public User validateUser(String username, String password) {
        User user = null;
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            
            ResultSet result = statement.executeQuery();
            
            if (result.next()) {
                user = new User();
                user.setId(result.getInt("id"));
                user.setUsername(result.getString("username"));
                user.setEmail(result.getString("email"));
            }
            
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}