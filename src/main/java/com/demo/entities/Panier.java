package com.demo.entities;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity // this one for hibernate
@Table(name = "TPanier") // this one for our table in the db
public class Panier {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPanier;
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "idClient")
	private Client client;

	@Column(columnDefinition = "Decimal(10,2) ")
	private Double prixPanier = 0.000;

	public Panier() {
		// TODO Auto-generated constructor stub
	}

	public Panier(Client client, Double prixPanier) {
		super();
		this.client = client;
		this.prixPanier = prixPanier;
		// this.listProduit = listProduit;
	}

	public Panier(Client client) {
		super();
		this.client = client;

		// this.listProduit = listProduit;
	}

	public Long getIdPanier() {
		return idPanier;
	}

	public void setIdPanier(Long idPanier) {
		this.idPanier = idPanier;
	}

	public Client getClient() {
		return client;
	}

	public void setIdClient(Client client) {
		this.client = client;
	}

	public Double getPrixPanier() {
		return prixPanier;
	}

	public void setPrixPanier(Double prixPanier) {

		this.prixPanier = prixPanier;
	}

}
