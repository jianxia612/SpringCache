package com.whdcmap.example.code.entity;

import java.io.Serializable;

/***
*@purpose:用户实体类
*@author:jianxiapc
*@since:2019年2月27日
***/
public class User implements Serializable{
	private Long id;
	
	private String name;
	
	private int age;
	
	private String email;	

	public User(Long id, String name, String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}	
	
	
}
