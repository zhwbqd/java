package com.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@SequenceGenerator(name="seqAccNo",sequenceName="DB_seqAccNo" , initialValue=1 , allocationSize=10)

public class Boss {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqAccNo")
	private int id;
	private String name;
	private int age;
	@Temporal(TemporalType.TIMESTAMP)
	private Date birth;
	private String projectcode;
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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public void setProjectcode(String projectcode) {
		this.projectcode = projectcode;
	}
	public String getProjectcode() {
		return projectcode;
	}
}
