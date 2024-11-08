package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.example.model.User;

public class UserDAO {
	public Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/useraccess", "postgres", "root");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public void addUser(User user) {
		Connection con = getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("INSERT INTO users (username, password, role) VALUES (?,?,?)");
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getRole());
			
			boolean result = ps.execute();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public User verify(String username, String password) {
		Connection con = getConnection();
		User user = new User();
		try {
			if(con!=null) {
				PreparedStatement ps = con.prepareStatement("Select * from users where username=?");
				ps.setString(1, username);
				ResultSet rs = ps.executeQuery();
				System.out.println(rs.toString());
				while(rs.next()) {
					System.out.println(rs.getString("password"));
					if(rs.getString("password").equals(password)) {
						user.setId(rs.getInt("id"));
						user.setUsername(rs.getString("username"));
						user.setPassword(rs.getString("password"));
						user.setRole(rs.getString("role"));
						return user;
					}
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
