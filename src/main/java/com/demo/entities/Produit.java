package com.demo.entities;

import javax.persistence.*;

import org.springframework.data.annotation.CreatedDate;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "T_Produits")
public class Produit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idProduit")
	private long id;
	private String name;
	@Column(length = 255)
	private String shortDescription;
	private float price;
	private int stock;
	private boolean status;
	@Lob
	private byte[] image;
	@ManyToOne
	@JoinColumn(name = "idCategorie", nullable = false)
	private Categorie category;
	@CreatedDate
	private Date dateCreation;

	@ManyToMany
	@JoinTable(name = "T_Commande_Produit", joinColumns = @JoinColumn(name = "idProduit"), inverseJoinColumns = @JoinColumn(name = "idCommande"))
	private List<Commande> commandes;

	public Produit() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Produit(String name, String shortDescription, float price, int stock, boolean status, byte[] image,
			Categorie categorie) {
		super();
		this.name = name;
		this.shortDescription = shortDescription;
		this.price = price;
		this.stock = stock;
		this.status = status;
		this.image = image;
		this.category = categorie;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public byte[] getImage() {

		return image;
	}

	public void setImage(byte[] bs) {
		this.image = bs;
	}

	public Categorie getCategory() {
		return category;
	}

	public void setCategory(Categorie categorie) {
		this.category = categorie;
	}

}
