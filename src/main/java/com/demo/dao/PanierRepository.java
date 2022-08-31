package com.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.entities.Client;
import com.demo.entities.Panier;

@Repository // this interface is responsible for data access
public interface PanierRepository extends JpaRepository<Panier, Long> {

	@Query("select p from Panier p where p.client = ?1")
	public Client findClientPanier(Client client);

	@Query("select p from Panier p where p.idPanier = ?1")
	public Panier findByIdPanier(Long idPanier);

	@Query("update Panier p set prixPanier = ?1")
	public void updatePrixPanier(Double prixPanier);

	@Transactional
	@Modifying
	@Query("update Panier p set p.prixPanier = ?1 where p.idPanier = ?2")
	public void updatePrixPaniers(double prixPanier, Long idPanie);

	@Modifying
	@Transactional
	@Query("delete from Panier c where c.idPanier = ?1")
	public void deletePanier(Long id);

	@Query("SELECT max(c.idPanier) FROM Panier c")
	public long getPnierMaxid();

}
