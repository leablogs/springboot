package com.leablogs.pojo;

public class User {
	private int id;
	private String username;
	private String note;
	private int sex;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", note=" + note + ", sex=" + sex + "]";
	}

}
