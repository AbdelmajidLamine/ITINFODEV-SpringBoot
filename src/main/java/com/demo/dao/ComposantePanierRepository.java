package com.demo.dao;

import java.util.List;

import com.demo.bo.productDashboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.entities.ComposantePanier;
import com.demo.entities.Panier;

@Repository //responsible for data access
public interface ComposantePanierRepository extends JpaRepository<ComposantePanier, Long> {
	
	@Query( "select panier.idPanier from ComposantePanier where idComposante =?1")
	public Long findIdPanierComposant(long idComposante);
	
	
	@Query( "select produit.id from ComposantePanier where idComposante =?1")
	public Long findProduitComposante( long id);
	
	@Query("from ComposantePanier where idComposante = ?1")
	public ComposantePanier findByIdComposante(Long idComposante);
	
	
	@Query("from ComposantePanier")
	public List<ComposantePanier> findAll();

	@Query(value = "select new com.demo.bo.productDashboard(count(p.idComposante) ,SUM(p.quantite),p.produit.name ,p.produit.category.nomCategorie,p.produit.price) from ComposantePanier p where p.produit.id=:idProduct")
	public List<productDashboard> getByProducts(@Param("idProduct") long idProduct);
	
	@Query( "from ComposantePanier c where c.panier.idPanier = :idp")
	public List<ComposantePanier> findPanierComposante(@Param("idp") Long id);
	
	@Modifying
    @Transactional
	@Query( "delete from ComposantePanier c where c.idComposante = ?1")
	public void deleteComp(Long id);
	
	    @Transactional
		@Modifying
		@Query ("update ComposantePanier c set c.quantite = ?1 where c.idComposante = ?2")
		public void updQuantitComposantePanier(int quantite , long idPanier );
	 
	    @Transactional
		@Modifying
		@Query ("update ComposantePanier c set c.panier = ?1 where c.idComposante = ?2")
		public void updtIdPanier(Panier panier , long idCompsoante );
	
	
	
}
