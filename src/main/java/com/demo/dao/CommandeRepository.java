package com.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.entities.Client;
import com.demo.entities.Commande;

@Repository
@Transactional
public interface CommandeRepository extends JpaRepository<Commande, Long> {

    @Query("select c from Commande c where c.idCommande = ?1")
    public Commande findCommandeByID(long id);

	@Query("select c from Commande c where c.mail LIKE :mail")
	public List<Commande> findByEmailCommande(@Param("mail") String mail);

	@Query("select c.livraison from Commande c where c.idCommande =:idCommande")
	public String findByLivraisonCommande(@Param("idCommande") long idCommande);
	
	
	@Transactional
		@Modifying
		@Query ("update Commande u set u.livraison = ?1 where u.idCommande = ?2")
		public void livreCommande(String livre , long id );
	
}
