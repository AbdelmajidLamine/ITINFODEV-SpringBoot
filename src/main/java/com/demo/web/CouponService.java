package com.demo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dao.CouponRepository;
import com.demo.entities.Coupon;

@RestController
@CrossOrigin(origins = "http://localhost:4000")
public class CouponService {

	@Autowired
	private CouponRepository couponRepository;

	@GetMapping("/coupon")
	public List<Coupon> getAll() {

		return couponRepository.findAll();
	}

	@GetMapping("/coupon/{id}")
	public Coupon getCouponBayId(@PathVariable long id) {
		return couponRepository.findByIdCoupon(id);
	}

	@GetMapping("/coupons/{coupon}")
	public ResponseEntity<Coupon> getCouponBayCoupon(@PathVariable String coupon) {

		Coupon c = couponRepository.findByCoupon(coupon);

		if (c == null) {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		}

		else {

			return new ResponseEntity<>(c, HttpStatus.OK);
		}

	}

}
