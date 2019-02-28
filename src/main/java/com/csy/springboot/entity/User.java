package com.csy.springboot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="t_user")
public class User  {

	@Id
	private long id;
	@Column(length=100)
	private String name;
	@Column(length=100)
	private String account;
	@Column(length=100)
	private String pwd;
	@Column(length=100)
	private String age;
	@Column(length=100)
	private String sex;
	
}
