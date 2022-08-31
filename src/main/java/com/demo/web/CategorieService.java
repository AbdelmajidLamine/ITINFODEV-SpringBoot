package com.demo.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dao.CategorieRepository;
import com.demo.entities.Categorie;
import com.demo.entities.Produit;

@RestController
@CrossOrigin(origins = "http://localhost:4000")

public class CategorieService {
	@Autowired
	CategorieRepository categorieRepository;

	@PostMapping("/new-categorie")
	public ResponseEntity<Categorie> addCategorie(@RequestParam("nomCategorie") String nomCategorie,
			@RequestParam("slag") String slag) {

		try {
			categorieRepository.save(new Categorie(nomCategorie, slag));
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@DeleteMapping("/categories/{id}")
	public void deleteCategorie(@PathVariable Long id) {

		categorieRepository.deleteById(id);

	}

	@GetMapping("/categories")
	public List<Categorie> getAllCategorie() {

		return categorieRepository.findAll();
	}

	@GetMapping("/categories/{nomCategorie}")
	public List<Categorie> findByNomCategorie(String nom) {

		return categorieRepository.findByNomCategorie(nom);

	}

	@GetMapping("/categories/{id}")
	public Optional<Categorie> findByIdCategorie(Long id) {
		return categorieRepository.findById(id);

	}



}
