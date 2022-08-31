package com.demo.dao;

import com.demo.entities.notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface NotificationDao extends JpaRepository<notification,Long> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update notification notification set notification.readNot=:type where notification.id=:id")
    public void updateNot(@Param("type") boolean type,@Param("id") long id);

}
