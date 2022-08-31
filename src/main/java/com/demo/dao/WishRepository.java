package com.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.demo.entities.Wish;
import org.springframework.stereotype.Repository;

@Repository
public interface WishRepository extends JpaRepository<Wish, Long> {
	@Query( "select idWish,idClient from Wish where idClient =:idC")
	public Long findClientWish(@Param("idC") Long id);
}
