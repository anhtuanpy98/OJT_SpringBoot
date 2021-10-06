package com.java.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import com.java.audit.Auditable;



@Entity
public class StudentV2 extends Auditable<String>{
	@Id
	private int id;
	
	private String name;
	
	private String address;
	
	private String gender;
	
	

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	//@NotBlank(message = "Bỉthday is mandatory")
	public Date birthday;

	
	
	public StudentV2() {
		
	}

	

	public StudentV2(int id, String name, String address, String gender, Date birthday) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.gender = gender;
		this.birthday = birthday;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	
}
