package vn.edu.hcmuaf.fit.webthitracnghiem.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "account")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	private String username;
	private String password;
	private String email;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "role")
	private Role isAdmin;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "status")
	private Status status;

	public Account() {

	}

	public Account(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;

	}

	public Account(String username, String password, String email, Role isAdmin, Status status) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.isAdmin = isAdmin;
		this.status = status;
	}

	public int getUserId() {
		return userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Role isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", email=" + email
				+ "]";
	}

}
