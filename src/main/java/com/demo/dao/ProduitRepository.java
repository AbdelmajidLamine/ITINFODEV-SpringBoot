package com.demo.dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.entities.Categorie;
import com.demo.entities.Produit;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long>{
	
	@Query("SELECT p FROM Produit p WHERE CONCAT(p.name, p.shortDescription, p.price, p.category.nomCategorie) LIKE %?1%")
	public List<Produit> search(String keyword);
		
	@Query( "select p from Produit p where p.id = ?1")
	public Produit findByIdProduit (long idProduit);
	
	@Query("select p from Produit p where p.name like %?1% ")
    public List<Produit> findByDesignationProduits ( String designation);
	
	@Transactional
	@Modifying
	@Query ("update Produit p set p.price = ?1 where p.id = ?2")
	public void updatePrixProduit(float price , long idProduct );
	
	@Transactional
	@Modifying
	@Query ("update Produit p set p.stock  = ( p.stock  + ?1 ) where p.id = ?2")
	public void updateQuantiteProduit(int stock  , long idProduit );
	
	@Transactional
	@Modifying
	@Query ("update Produit p set p.status = ?1 where p.id = ?2")
	public void updateStatusProduit(boolean status , long idProduit );
	
	//@Query( "select p from Produit p where p.category = ?1")
	 //public List<Produit> findByCategory(Categorie categorie);
	
	@Query( "select p from Produit p where p.category.nomCategorie like %?1% ")
	public List<Produit> findByCategory(String nomCategorie);

}
