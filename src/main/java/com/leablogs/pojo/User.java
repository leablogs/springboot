package com.leablogs.pojo;

import org.apache.ibatis.type.Alias;

import com.leablogs.enumeration.SexEnum;

@Alias("user")
public class User {
	private int id;
	private String username;
	private String note;
	private SexEnum sex;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public SexEnum getSex() {
		return sex;
	}

	public void setSex(SexEnum sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", note=" + note + ", sex=" + sex + "]";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
