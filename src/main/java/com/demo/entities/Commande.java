package com.demo.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Commande {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idCommande;
	private String firstName;
	private String lastName;
	private String companyName;
	private String adresse;
	private String adresse_2;
	private String country;
	private String city;
	private String phone;
	private String mail;
	private String codePostal;
	private String notes;
	private String livraison;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date drive;

	@Temporal(TemporalType.TIME)
	private Date heurRamassage;

	private double totalPrice;
	@ManyToMany
	@JoinTable(name = "T_Commande_Produit", joinColumns = @JoinColumn(name = "idCommande"), inverseJoinColumns = @JoinColumn(name = "idComposante")

	)

	private List<ComposantePanier> products;

//inverseJoinColumns

	public Commande(String firstName, String lastName, String companyName, String adresse, String adresse_2,
			String country, String city, String phone, String mail, String codePostal, String notes, Date drive,
			Date heurRamassage, double totalPrice, List<ComposantePanier> products,String livraison) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.companyName = companyName;
		this.adresse = adresse;
		this.adresse_2 = adresse_2;
		this.country = country;
		this.city = city;
		this.phone = phone;
		this.mail = mail;
		this.codePostal = codePostal;
		this.notes = notes;
		this.drive = drive;
		this.heurRamassage = heurRamassage;
		this.totalPrice = totalPrice;
		this.products = products;
		this.livraison=livraison;

	}

	public Commande(String firstName, String lastName, String companyName, String adresse, String adresse_2,
			String country, String city, String phone, String mail, String codePostal, String notes, String livraison,
			Date drive, Date heurRamassage, double totalPrice, List<ComposantePanier> products) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.companyName = companyName;
		this.adresse = adresse;
		this.adresse_2 = adresse_2;
		this.country = country;
		this.city = city;
		this.phone = phone;
		this.mail = mail;
		this.codePostal = codePostal;
		this.notes = notes;
		this.livraison = livraison;
		this.drive = drive;
		this.heurRamassage = heurRamassage;
		this.totalPrice = totalPrice;
		this.products = products;
	}

	public String getAdresse_2() {
		return adresse_2;
	}

	public void setAdresse_2(String adresse_2) {
		this.adresse_2 = adresse_2;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public Date getDrive() {
		return drive;
	}

	public void setDrive(Date drive) {
		this.drive = drive;
	}

	public Date getHeurRamassage() {
		return heurRamassage;
	}

	public void setHeurRamassage(Date heurRamassage) {
		this.heurRamassage = heurRamassage;
	}

	public String getLivraison() {
		return livraison;
	}

	public void setLivraison(String livrraison) {
		this.livraison = livrraison;
	}

	public Commande() {
	}

	public Commande(String firstName, String lastName, String companyName, String adresse, String city, String phone,
			String mail, String notes, double totalPrice, String country) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.companyName = companyName;
		this.adresse = adresse;
		this.city = city;
		this.phone = phone;
		this.mail = mail;
		this.notes = notes;
		this.country = country;
		this.totalPrice = totalPrice;
	}

	public Commande(String firstName, String lastName, String companyName, String adresse, String country, String city,
			String phone, String mail, String codePostal, String notes, Date drive, Date heurRamassage,
			double totalPrice) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.companyName = companyName;
		this.adresse = adresse;
		this.country = country;
		this.city = city;
		this.phone = phone;
		this.mail = mail;
		this.codePostal = codePostal;
		this.notes = notes;
		this.drive = drive;
		this.heurRamassage = heurRamassage;
		this.totalPrice = totalPrice;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public long getIdCommande() {
		return idCommande;
	}

	public void setIdCommande(long idCommande) {
		this.idCommande = idCommande;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public List<ComposantePanier> getProducts() {
		return products;
	}

	public void setProducts(List<ComposantePanier> products) {
		this.products = products;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Commande(String firstName, String lastName, String companyName, String adresse, String country, String city,
			String phone, String mail, String codePostal, String notes, Date drive, Date heurRamassage,
			double totalPrice, List<ComposantePanier> products) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.companyName = companyName;
		this.adresse = adresse;
		this.country = country;
		this.city = city;
		this.phone = phone;
		this.mail = mail;
		this.codePostal = codePostal;
		this.notes = notes;
		this.drive = drive;
		this.heurRamassage = heurRamassage;
		this.totalPrice = totalPrice;
		this.products = products;
	}

	public Commande(String firstName, String lastName, String companyName, String adresse, String adresse_2,
			String country, String city, String phone, String mail, String codePostal, String notes, Date drive,
			Date heurRamassage, double totalPrice) {

		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.companyName = companyName;
		this.adresse = adresse;
		this.country = country;
		this.city = city;
		this.phone = phone;
		this.mail = mail;
		this.codePostal = codePostal;
		this.notes = notes;
		this.drive = drive;
		this.heurRamassage = heurRamassage;
		this.totalPrice = totalPrice;
	}

}
