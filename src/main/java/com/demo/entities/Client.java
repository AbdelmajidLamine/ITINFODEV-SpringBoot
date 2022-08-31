package com.demo.entities;

import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity

public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long idClient;
	private String name;
	private String email;
	private String password;
	private String addresse;
	private String tel;
	@Lob
	private byte[] image;

	@OneToOne
	@JoinColumn(name = "idPanier")
	private Panier panier;

	private String codePin;

	public Client(Long idClient, String name, String email, String password, String addresse, String tel, byte[] image,
			Panier panier) {
		super();
		this.idClient = idClient;
		this.name = name;
		this.email = email;
		this.password = password;
		this.addresse = addresse;
		this.tel = tel;
		this.image = image;
		this.panier = panier;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getCodePin() {
		return codePin;
	}

	public void setCodePin(String codePin) {
		this.codePin = codePin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Panier getPanier() {

		return panier;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPanier(Panier panier) {
		panier.setIdPanier(idClient);
		this.panier = panier;
	}

	public String getAddresse() {
		return addresse;
	}

	public void setAddresse(String addresse) {
		this.addresse = addresse;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Client() {
		super();
	}

	public Client(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public Client(String name, String email, String password, String addresse, String tel, Panier panier) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.addresse = addresse;
		this.tel = tel;
		this.panier = panier;
	}

	public Client(String name, String email, String password, Panier panier) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.panier = panier;
	}

	public Client(String name, String mail, String password) {
		this.name = name;
		this.email = mail;
		this.password = password;

	}

	public Long getIdClient() {
		return idClient;
	}

	public void setIdClient(Long idClient) {
		this.idClient = idClient;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Client [idClient=" + idClient + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", addresse=" + addresse + ", tel=" + tel + ", image=" + image + ", codePin=" + codePin + ", panier="
				+ panier + "]";
	}

}
