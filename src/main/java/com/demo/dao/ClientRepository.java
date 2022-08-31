package com.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.entities.Admin;
import com.demo.entities.Client;
import com.demo.entities.Panier;


@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

	//@Query( "select admin from Admin admin where admin.email Like:email")
	//public Admin findAdminByEmail(@Param("email") String email);
	
	public Client findClientByIdClient(long id);
	@Query( " select count (idClient) from Client where email Like:pmail")
	public Integer mailCount(@Param("pmail")String mail);
	
	@Query( " select count (idClient) from Client where pseudo Like:ppseudo")
	public Integer pseudoCount(@Param("ppseudo")String mail);
	
	@Query( " select count (idClient) from Client where email Like:pmail and password like:pmdp")
	public Integer login(@Param("pmail")String mail,@Param("pmdp")String mdp);
	
	@Query( "select client from Client client where client.email Like:email")
	public Client    findClientByEmail(@Param("email") String email);
	
	@Query( "select client from Client client where client.codePin Like:codePin")
	 public Client findByResetPasswordCodePin( @Param("codePin") String codePin);
	
	@Transactional
	@Modifying
	@Query ("update Client p set p.password = ?1 where p.id = ?2")
	public void updatePasswordClient(String  password , long idProduct );
	
	@Transactional
	@Modifying
	@Query ("update Client p set p.panier = ?1 where p.id = ?2")
	public void updatePanierClient( Panier panier, long idClient );

	@Modifying(clearAutomatically = true)
	@Query("update Client  cl set cl.image=:image, cl.name=:name , cl.email=:email ,cl.addresse=:addresse,cl.tel=:tel where cl.idClient=:idClient")
	public void updateImageClient(@Param("image") byte[] image,@Param("name") String pseudo,@Param("email") String email,@Param("addresse") String addresse,@Param("tel") String tel,@Param("idClient") long idClient);
}



