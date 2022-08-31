package com.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.entities.Client;
import com.demo.entities.Coupon;
import org.springframework.stereotype.Repository;


@Repository
public interface CouponRepository  extends JpaRepository<Coupon, Long> {
	
	@Query("select p from Coupon p where p.idCoupon = ?1")
	public Coupon findByIdCoupon(Long idCoupon);
	
	//@Query( "select c from Client c where client.codePin Like:codePin")
	 public Coupon findByCoupon( String codePin);
	
	
}
