package com.solt.police.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.solt.police.entity.Police;
import com.solt.police.utility.DatabaseManager;

public class PoliceService {
	
	

	public Police findLoginname(String n) {
		// TODO Auto-generated method stub
		String sql = "select * from police where login_id=?";
		try(Connection con = DatabaseManager.getConnection();
				PreparedStatement stmt =con.prepareStatement(sql)) {
			stmt.setString(1, n);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Police police = new Police();
				police.setLoginid(rs.getString("login_id"));
				police.setName(rs.getString("name"));
				police.setPassword(rs.getString("password"));
				police.setBirthday(rs.getDate("birthday"));
				police.setPosition(rs.getString("position"));
				police.setAddress(rs.getString("address"));
				return police;
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	public void add(Police police) {
		String sql = "insert into police (login_id,name,password,birthday,position,address)values(?,?,?,?,?,?);";
		try(Connection con = DatabaseManager.getConnection();
				PreparedStatement stmt = con.prepareStatement(sql)) {
				stmt.setString(1, police.getLoginid());
				stmt.setString(2, police.getName());
				stmt.setString(3, police.getPassword());
				stmt.setDate(4, police.getBirthday());
				stmt.setString(5, police.getPosition());
				stmt.setString(6, police.getAddress());
				stmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public List<Police>findAll() {
		// TODO Auto-generated method stub
		List<Police>list = new ArrayList<Police>();
		String sql = "select * from police";
		try (Connection con = DatabaseManager.getConnection();
				PreparedStatement stmt = con.prepareStatement(sql)){
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Police police = new Police();
				police.setLoginid(rs.getString("login_id"));
				police.setName(rs.getString("name"));
				police.setBirthday(rs.getDate("birthday"));
				police.setPosition(rs.getString("position"));
				police.setAddress(rs.getString("address"));
				list.add(police);
			}
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	public void update(Police police) {
		// TODO Auto-generated method stub
		String sql = "update police set name=?,password=?,birthday=?,position=?,address=? where login_id=?";
		
		try(Connection con = DatabaseManager.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setString(1, police.getName());
			stmt.setString(2, police.getPassword());
			stmt.setDate(3, police.getBirthday());
			stmt.setString(4, police.getPosition());
			stmt.setString(5, police.getAddress());
			stmt.setString(6, police.getLoginid());
			stmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void delete(Police police) {
		String sql = "delete from police where login_id=?";
		try(Connection con = DatabaseManager.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.setString(1, police.getLoginid());
			stmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public List<Police> find(String text) {
		StringBuffer sb = new StringBuffer("Select * from police p ");
		List<Police>list = new ArrayList<Police>();
		List<Object>param = new ArrayList<Object>();
		
		if (!text.isEmpty()||text!=null) {
			sb.append("where p.login_id like ? ");
			param.add(text.concat("%"));
		}
				
		try(Connection con = DatabaseManager.getConnection();
				PreparedStatement stmt = con.prepareStatement(new String(sb))) {
			for (int i = 0; i < param.size(); i++) {
				stmt.setObject(i+1, param.get(i));
				
			}
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Police police = new Police();
				police.setLoginid(rs.getString("login_id"));
				police.setName(rs.getString("name"));
				police.setBirthday(rs.getDate("birthday"));
				police.setPosition(rs.getString("position"));
				police.setAddress(rs.getString("address"));
				list.add(police);
			}
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
		// TODO Auto-generated method stub
		
	}
}
