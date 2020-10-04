package com.upemor.sesioncuatro.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="MEMBER")
public class Member {

	@Id
	@GeneratedValue
	@Column(name="ID")
	private int id;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "NAME")
	private String name;
	
	
	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name="CREATED")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private Date createdDate;
	
	@ManyToMany()
	@JoinTable(
	name = "MEMBER_ROLES",
	joinColumns = {@JoinColumn(name = "MEMBER_ID")}, 
	inverseJoinColumns = {@JoinColumn(name = "ROLE_ID")})
	private List<Role> role;
	
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the active
	 */
	//public boolean isActive() {
	//	return active;
	//}

	/**
	 * @param active the active to set
	 */
	//public void setActive(boolean active) {
	//	this.active = active;
	//}

	/**
	 * @return the role
	 */
	public List<Role> getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(List<Role> role) {
		this.role = role;
	}

	public Member() {
		// TODO Auto-generated constructor stub
	}
	
}