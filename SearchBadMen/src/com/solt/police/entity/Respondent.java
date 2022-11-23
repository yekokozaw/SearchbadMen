package com.solt.police.entity;

import java.sql.Date;

public class Respondent {
	public String crimeid;
	public String photo;
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String name;
	public Date birthday;
	public String crime;
	public String contact;
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String address;
	public String policename;
	
	public String getCrimeid() {
		return crimeid;
	}
	public void setCrimeid(String crimeid) {
		this.crimeid = crimeid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCrime() {
		return crime;
	}
	public void setCrime(String crime) {
		this.crime = crime;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPolicename() {
		return policename;
	}
	public void setPolicename(String policename) {
		this.policename = policename;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday =birthday;
	}
}
