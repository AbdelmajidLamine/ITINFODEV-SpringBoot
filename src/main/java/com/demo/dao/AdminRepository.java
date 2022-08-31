package com.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.entities.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
	
	@Query( "select admin from Admin admin where admin.email Like:email")
	public Admin findAdminByEmail(@Param("email") String email);
	
	@Query( "select admin from Admin admin where admin.idadmin= ?1")
	public Admin findAdminById(long idadmin);

	@Modifying(clearAutomatically = true)
	@Query("update Admin  ad set ad.image=:image, ad.pseudo=:pseudo , ad.email=:email where ad.idadmin=:idadmin")
	public void updateImage(@Param("image") byte[] image,@Param("pseudo") String pseudo,@Param("email") String email,@Param("idadmin") long idadmin);
	
	

	@Transactional
	@Modifying
	@Query ("update Admin p set p.password = ?1 where p.idadmin = ?2")
	public void updatePasswordAdmin(String  newPassword , long idadmin );
	
	
}
