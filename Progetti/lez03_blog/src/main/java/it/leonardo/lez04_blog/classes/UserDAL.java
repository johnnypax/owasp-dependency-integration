package it.leonardo.lez04_blog.classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAL {
	private Connection getConnection() throws SQLException {
        // Modifica con i dettagli del tuo database
        String url = "jdbc:mysql://localhost:3306/yourdatabase";
        String username = "yourusername";
        String password = "yourpassword";
        return DriverManager.getConnection(url, username, password);
    }

    public boolean insert(User user) throws SQLException {
        String sql = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
        
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getEmail());
            
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    public ArrayList<User> findAll() throws SQLException {
        String sql = "SELECT * FROM users";
        ArrayList<User> userList = new ArrayList<>();
        
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                User user = new User(username, password, email);
                userList.add(user);
            }
        }
        
        return userList;
    }
}
