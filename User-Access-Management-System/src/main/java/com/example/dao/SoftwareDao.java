package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Software;

public class SoftwareDao {
	public Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/useraccess", "postgres", "root");
		}catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public List<Software> getSoftwares(){
		List<Software> softwares = new ArrayList<Software>();
		Connection con = getConnection();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from software");
			while(rs.next()) {
				Software s = new Software();
				s.setId(rs.getInt("id"));
				s.setName(rs.getString("name"));
				s.setDescription(rs.getString("description"));
				s.setAccessLevels(rs.getString("access_levels"));
				softwares.add(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return softwares;
	}
	
	public void createSoftware(Software software) {
		Connection con = getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("insert into software (name,description,access_levels) values(?,?,?)");
			ps.setString(1, software.getName());
			ps.setString(2, software.getDescription());
			ps.setString(3, software.getAccessLevels());
			boolean result = ps.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void alterSoftware(int id,String access) {
		Connection con = getConnection();
		try {
			Statement st = con.createStatement();
			PreparedStatement ps = con.prepareStatement("UPDATE software SET access_levels=? WHERE id = ?");
			ps.setString(1, access);
			ps.setInt(2, id);
			boolean rs = ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
