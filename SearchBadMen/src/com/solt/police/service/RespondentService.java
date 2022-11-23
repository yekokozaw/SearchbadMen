package com.solt.police.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.solt.police.entity.Respondent;
import com.solt.police.utility.DatabaseManager;

public class RespondentService {
	
	public void add(Respondent respondent) {
		// TODO Auto-generated method stub
		String sql = "insert into badmen (crime_id,photo,name,birthday,crime,contact,address,Police_name)values(?,?,?,?,?,?,?,?);";
		try(Connection con = DatabaseManager.getConnection();
				PreparedStatement stmt = con.prepareStatement(sql)) {
				stmt.setString(1, respondent.getCrimeid());
				stmt.setString(2, respondent.getPhoto());
				stmt.setString(3, respondent.getName());
				stmt.setDate(4, respondent.getBirthday());
				stmt.setString(5, respondent.getCrime());
				stmt.setString(6, respondent.getContact());
				stmt.setString(7, respondent.getAddress());
				stmt.setString(8, respondent.getPolicename());
				stmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public List<Respondent> findAll() {
		// TODO Auto-generated method stub
		List<Respondent>list = new ArrayList<Respondent>();
		String sql = "select * from badmen";
		try (Connection con = DatabaseManager.getConnection();
				PreparedStatement stmt = con.prepareStatement(sql)){
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Respondent respondent = new Respondent();
				respondent.setCrimeid(rs.getString("crime_id"));
				respondent.setPhoto(rs.getString("photo"));
				respondent.setName(rs.getString("name"));
				respondent.setBirthday(rs.getDate("birthday"));
				respondent.setCrime(rs.getString("crime"));
				respondent.setContact(rs.getString("contact"));
				respondent.setAddress(rs.getString("address"));
				respondent.setPolicename(rs.getString("Police_name"));
				list.add(respondent);
			}
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	public List<Respondent> find(String text) {
		StringBuffer sb = new StringBuffer("Select * from badmen bm ");
		List<Respondent>list = new ArrayList<Respondent>();
		List<Object>param = new ArrayList<Object>();
		
		if (!text.isEmpty()||text!=null) {
			sb.append("where bm.name like ? ");
			param.add(text.concat("%"));
		}
				
		try(Connection con = DatabaseManager.getConnection();
				PreparedStatement stmt = con.prepareStatement(new String(sb))) {
			for (int i = 0; i < param.size(); i++) {
				stmt.setObject(i+1, param.get(i));
				
			}
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Respondent respondent = new Respondent();
				respondent.setName(rs.getString("name"));
				respondent.setBirthday(rs.getDate("birthday"));
				respondent.setCrime(rs.getString("crime"));
				respondent.setAddress(rs.getString("address"));
				list.add(respondent);
			}
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
		// TODO Auto-generated method stub
		
	}
	
	public List<Respondent> findcrimeid(String crimeid) {
		StringBuffer sb = new StringBuffer("Select * from badmen bm ");
		List<Respondent>list = new ArrayList<Respondent>();
		List<Object>param = new ArrayList<Object>();
		
		if (!crimeid.isEmpty()||crimeid!=null) {
			sb.append("where bm.crime_id like ? ");
			param.add(crimeid.concat("%"));
		}
				
		try(Connection con = DatabaseManager.getConnection();
				PreparedStatement stmt = con.prepareStatement(new String(sb))) {
			for (int i = 0; i < param.size(); i++) {
				stmt.setObject(i+1, param.get(i));
				
			}
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Respondent respondent = new Respondent();
				respondent.setCrimeid(rs.getString("crime_id"));
				respondent.setPhoto(rs.getString("photo"));
				respondent.setName(rs.getString("name"));
				respondent.setBirthday(rs.getDate("birthday"));
				respondent.setCrime(rs.getString("crime"));
				respondent.setContact(rs.getString("contact"));
				respondent.setAddress(rs.getString("address"));
				respondent.setPolicename(rs.getString("Police_name"));
				list.add(respondent);
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
