package com.demo.entities;

import javax.persistence.*;

@Entity
@Table(name = "T_Admins")
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idadmin;
	@Column(name = "pseudo")
	private String pseudo;
	@Column(name = "email", unique = true)
	private String email;
	private String password;
	@Lob
	private byte[] image;

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public byte[] getImage() {
		return image;
	}

	@Override
	public String toString() {
		return "Admin{" +
				"idadmin=" + idadmin +
				", pseudo='" + pseudo + '\'' +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				", image='" + image + '\'' +
				'}';
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Admin(String pseudo, String email, String password, byte[] image) {
		super();
		this.pseudo = pseudo;
		this.email = email;
		this.password = password;
		this.image = image;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getIdadmin() {
		return idadmin;
	}

	public void setIdadmin(long id_admin) {
		this.idadmin = id_admin;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
