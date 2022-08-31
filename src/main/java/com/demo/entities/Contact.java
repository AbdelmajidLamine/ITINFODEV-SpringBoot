package com.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_Contact")
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idContact;
	@Column(name = "name")
	private String name;
	@Column(name = "email")
	private String email;
	private Boolean subjetCommande;
	private String tel;
	private String subject;
	private String description;
	private boolean readNot;

	@Override
	public String toString() {
		return "Contact{" +
				"idContact=" + idContact +
				", name='" + name + '\'' +
				", email='" + email + '\'' +
				", subjetCommande=" + subjetCommande +
				", tel='" + tel + '\'' +
				", subject='" + subject + '\'' +
				", description='" + description + '\'' +
				", read=" + readNot +
				'}';
	}

	public boolean isRead() {
		return readNot;
	}

	public void setRead(boolean read) {
		this.readNot = read;
	}

	public Boolean getSubjetCommande() {
		return subjetCommande;
	}

	public void setSubjetCommande(Boolean subjetCommande) {
		this.subjetCommande = subjetCommande;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public long getIdContact() {
		return idContact;
	}

	public void setIdContact(long idContact) {
		this.idContact = idContact;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Contact() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Contact(String name, String email, String tel, Boolean subjetCommande, String subject, String description,boolean read) {
		super();
		this.name = name;
		this.email = email;
		this.subjetCommande = subjetCommande;
		this.tel = tel;
		this.subject = subject;
		this.description = description;
		this.readNot=read;
	}

	public Contact(String name, String email, String subject, String description) {
		super();
		this.name = name;
		this.email = email;
		this.subject = subject;
		this.description = description;
	}

	public Contact(long idContact, String name, String email, String subject, String description) {
		super();
		this.idContact = idContact;
		this.name = name;
		this.email = email;
		this.subject = subject;
		this.description = description;
	}

}
