package com.demo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ComposantePanier {
	@Id
	@GeneratedValue
	private long idComposante;
	@ManyToOne
	@JoinColumn(name = "idPanier")
	private Panier panier;
	@ManyToOne
	@JoinColumn(name = "idProduit")
	private Produit produit;

	private int quantite;

	public ComposantePanier() {

	}

	public ComposantePanier(Panier panier, Produit produit, int quantite) {
		this.panier = panier;
		this.produit = produit;
		this.quantite = quantite;
	}

	public ComposantePanier(Panier panier, Produit produit) {
		this.panier = panier;
		this.produit = produit;
	}

	public ComposantePanier(Produit produit, int quantite) {
		super();
		this.produit = produit;
		this.quantite = quantite;
	}

	public Long getIdComposante() {
		return idComposante;
	}

	public void setIdComposante(Long idComposante) {
		this.idComposante = idComposante;
	}

	public Panier getPanier() {
		return panier;
	}

	public void setPanier(Panier panier) {
		this.panier = panier;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

}
