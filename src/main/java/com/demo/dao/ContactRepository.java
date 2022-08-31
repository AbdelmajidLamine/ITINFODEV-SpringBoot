package com.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.entities.Contact;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ContactRepository  extends JpaRepository<Contact, Long>{
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Contact c set c.readNot=:type where c.idContact=:id")
    public void updateMessage(@Param("type") boolean type,@Param("id") long id);

}
