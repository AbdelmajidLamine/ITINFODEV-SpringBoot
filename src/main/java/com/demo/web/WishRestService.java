package com.demo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dao.WishRepository;
import com.demo.entities.Wish;

@RestController
public class WishRestService {
	@Autowired
	private WishRepository wishes;
	
	@RequestMapping(value="/wishes",method=RequestMethod.GET)
	public List<Wish> getAll(){
		return wishes.findAll();
	}
	@RequestMapping(value="/wishes/{id}",method=RequestMethod.GET)
	public Long getWish(@PathVariable long id){
		return wishes.findClientWish(id);
	}
}
