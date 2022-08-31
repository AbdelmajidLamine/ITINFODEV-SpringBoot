
package com.demo.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.entities.Categorie;
import org.springframework.jdbc.core.JdbcTemplate;  
@Repository
public interface CategorieRepository extends JpaRepository<Categorie,Long> {

	
	public List<Categorie> findByNomCategorie(String nom);
	public Categorie findByIdCategorie(Long id);
	public Categorie findBySlag(String slag );

	
	 
	  @Modifying
	  @Transactional
	  @Query("delete Categorie u where u.nomCategorie =?1")
	  public  void deleteNomCategorie( String nom );
	 
	 
	    @Transactional
		@Modifying
		@Query ("update Categorie u set u.nomCategorie = ?1 where u.idCategorie = ?2")
		public void updNomCategorie(String nom , long id );
	 
	    @Transactional
		@Modifying
		@Query ("update Categorie u set u.slag = ?1 where u.idCategorie = ?2")
		public void updSlagCategorie(String slag , long id );

	
}



