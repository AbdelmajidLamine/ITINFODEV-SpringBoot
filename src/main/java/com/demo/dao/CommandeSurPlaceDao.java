package com.demo.dao;

import com.demo.entities.CommandeSurPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandeSurPlaceDao extends JpaRepository<CommandeSurPlace,Long> {
}
