package com.demo.entities;

import javax.persistence.*;

@Entity
@Table(name = "T_Categorie")
public class Categorie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idCategorie;
	private String nomCategorie;
	private String slag;

	public long getIdCategorie() {
		return idCategorie;
	}

	public void setIdCategorie(long idCategorie) {
		this.idCategorie = idCategorie;
	}

	public String getNomCategorie() {
		return nomCategorie;
	}

	public void setNomCategorie(String nomCategorie) {
		this.nomCategorie = nomCategorie;
	}

	public String getSlag() {
		return slag;
	}

	public void setSlag(String slag) {
		this.slag = slag;
	}

	public Categorie() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Categorie(String nomCategorie, String slag) {
		super();
		this.nomCategorie = nomCategorie;
		this.slag = slag;
	}

	@Override
	public String toString() {
		return "Categorie [idCategorie=" + idCategorie + ", nomCategorie=" + nomCategorie + ", slag=" + slag + "]";
	}

}
