package com.demo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Wish {
	@Id
	@GeneratedValue
	private Long idWish;
	private Long idClient;
	
	
	public Wish() {
		super();
	}

	public Wish(Long idWish, Long idClient) {
		super();
		this.idWish = idWish;
		this.idClient = idClient;
	}

	public Wish(Long idClient) {
		super();
		this.idClient = idClient;
	}

	public Long getIdWish() {
		return idWish;
	}

	public void setIdWish(Long idWish) {
		this.idWish = idWish;
	}

	public Long getIdClient() {
		return idClient;
	}

	public void setIdClient(Long idClient) {
		this.idClient = idClient;
	}
	

}
