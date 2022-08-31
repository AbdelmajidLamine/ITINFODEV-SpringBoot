package com.demo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_Coupon")
public class Coupon {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idCoupon;
	private String coupon;
	private double taux;
	private boolean active;

	public Coupon(String coupon, double taux, boolean active) {
		super();
		this.coupon = coupon;
		this.taux = taux;
		this.active = active;
	}

	public Coupon() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getCoupon() {
		return coupon;
	}

	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}

	public double getTaux() {
		return taux;
	}

	public void setTaux(double taux) {
		this.taux = taux;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public long getIdCoupon() {
		return idCoupon;
	}

}
