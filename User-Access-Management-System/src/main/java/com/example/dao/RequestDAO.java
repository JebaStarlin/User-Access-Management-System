package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Request;
import com.example.model.Software;

public class RequestDAO {
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
	
	public void addRequest(Request r) {
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("insert into requests (user_id,software_id,access_type,reason,status) values(?,?,?,?,?)");
			ps.setInt(1, r.getUserId());
			ps.setInt(2, r.getSoftwareId());
			ps.setString(3, r.getAccessType());
			ps.setString(4, r.getReason());
			ps.setString(5, r.getStatus());
			System.out.println(r.toString());
			boolean result = ps.execute();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Request> getRequests(){
		List<Request> requests = new ArrayList<>();
		Connection con = getConnection();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from requests");
			while(rs.next()) {
				Request r = new Request();
				r.setId(rs.getInt("id"));
				r.setUserId(rs.getInt("user_id"));
				r.setSoftwareId(rs.getInt("software_id"));
				r.setAccessType(rs.getString("access_type"));
				r.setReason(rs.getString("reason"));
				r.setStatus(rs.getString("status"));
				requests.add(r);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return requests;
	}
	
	public void alterRequest(int id,String status) {
		Request r = new Request();
		Connection con = getConnection();
		try {
			Statement st = con.createStatement();
			PreparedStatement ps = con.prepareStatement("UPDATE requests SET status = ? WHERE id = ?");
			ps.setString(1, status);
			ps.setInt(2, id);
			boolean rs = ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
