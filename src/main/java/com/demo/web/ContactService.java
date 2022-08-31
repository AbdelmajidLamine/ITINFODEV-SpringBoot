package com.demo.web;

import java.util.ArrayList;
import java.util.List;

import com.demo.entities.notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.demo.dao.ContactRepository;
import com.demo.entities.Contact;
import com.demo.entities.Produit;

@RestController
@CrossOrigin(origins = "http://localhost:4000")
public class ContactService {

	@Autowired
	ContactRepository contactRepository;

	@PostMapping("/contact")
	public ResponseEntity<Contact> addCategorie(@RequestParam("name") String name, @RequestParam("email") String email,
			@RequestParam("tel") String tel, @RequestParam("subjetCommande") boolean subjetCommande,
			@RequestParam("subject") String subject, @RequestParam("description") String description) {

		try {

			contactRepository.save(new Contact(name, email, tel, subjetCommande, subject, description,false));

			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping("/unreadMessages")
	public List<Contact> getUnreadMessages(){
		List<Contact> messages=contactRepository.findAll();
		List<Contact> newMessages=new ArrayList<>();
		for(Contact m:messages){
			if(!m.isRead()){
				newMessages.add(m);
			}
		}
		return newMessages;
	}
	@GetMapping("/updateMessage/{id}")
	public void updateMessage(@PathVariable long id){
		contactRepository.updateMessage(true,id);
	}

	@GetMapping(value = "/contacts")
	public List<Contact> getAll() {

		return contactRepository.findAll();
	}

}
