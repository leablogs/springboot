package com.leablogs.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import com.leablogs.enumeration.SexEnum;

@Alias("user1")
public class User1 implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	@NotNull(message = "name is not ulll")
	private String username;
	private String note;
	private SexEnum sex;
	@Email(message = "email is error")
	private String email;
	@NotNull(message = "age is notnull")
	@DecimalMin(value = "0", message = "min 0")
	@DecimalMax(value = "130", message = "max 130")
	private Integer age;
//	@Future(message = "need a future time")
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	@NotNull
	private Date date;
	@Size(min = 1, max = 100, message = "size need between 1 and 100")
	private String remark;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getSize() {
		return remark;
	}

	public void setSize(String remark) {
		this.remark = remark;
	}

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
		return "User1 [id=" + id + ", username=" + username + ", note=" + note + ", sex=" + sex + ", email=" + email
				+ ", age=" + age + ", date=" + date + ", remark=" + remark + "]";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
