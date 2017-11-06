package com.ajax.dbm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajax.model.User;

@Service
public class UserManager {
	
	private static final String TABLE = "users";
	@Autowired
	private PasswordUtility passwordUtility;
	
	public int addUser(User user) {
		Connection conn = DBConnection.connect();
		String query = "INSERT INTO " + TABLE + " (username, firstName, lastName, email, password) VALUES (?,?,?,?,?)";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, user.getUserName());
			stmt.setString(2, user.getFirstName());
			stmt.setString(3, user.getLastName());
			stmt.setString(4, user.getEmail());
			stmt.setString(5, user.getPassword());
			int rtval = stmt.executeUpdate();
			conn.commit();
			return rtval;
		} catch (SQLException e) {	
			e.printStackTrace();
		} finally {
			
		}
		
		return -1;
	}
	
	/**
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public User getUser(String username, String password) {
		User user = null;
		Connection conn = DBConnection.connect();
		String query = "SELECT * FROM " + TABLE + " WHERE username = ? AND password = ? LIMIT 1;";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, username);
			// turn password into a hash before querying
			stmt.setString(2, passwordUtility.getSecuredPassword(password));
			
			ResultSet rs = stmt.executeQuery();
			conn.commit();
			
			// construct user
			while(rs.next()) {
				user = new User(rs.getString("email"), rs.getString("userName"), rs.getString("firstName"), rs.getString("lastName")); 
				// user.setId(rs.getInt("id"));

				// limit 1
				break;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
}
