package com.demo.web;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.demo.dao.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dao.ComposantePanierRepository;
import com.demo.dao.PanierRepository;
import com.demo.entities.Panier;
import com.demo.entities.Produit;
import com.demo.entities.Admin;
import com.demo.entities.Client;
import com.demo.entities.ComposantePanier;

@RestController
@CrossOrigin(origins = "http://localhost:4000")
public class PanierService {

	@Autowired
	private PanierRepository panierRepository;
	@Autowired
	private ComposantePanierRepository comr;
	@Autowired
	private ClientRepository clientRepository;

	// private ArrayList<ComposantePanier> composantePaniers = new
	// ArrayList<ComposantePanier>();

	@GetMapping("/paniers")
	public List<Panier> getAllPaniers() {
		return panierRepository.findAll();
	}

	@GetMapping(value = "/Panier")
	public Client getClientPanier(Panier panier) {
		return panierRepository.findClientPanier(panier.getClient());

	}

	@GetMapping(value = "/paniers/{id}")
	public Panier getPanier(@PathVariable long id) {
		return panierRepository.findByIdPanier(id);

	}

	@PutMapping(value = "/modifyPanierPrice")
	public void modifiel(@RequestParam("id") long id) {
		ArrayList<ComposantePanier> composantePaniers = new ArrayList<ComposantePanier>();

		float prixtotal = 0;
		composantePaniers.addAll(comr.findPanierComposante(id));

		for (ComposantePanier composantePanier : composantePaniers) {

			prixtotal += composantePanier.getQuantite() * composantePanier.getProduit().getPrice();
		}

		panierRepository.updatePrixPaniers(prixtotal, id);

	}

	private static DecimalFormat df2 = new DecimalFormat("#.##");

	@PutMapping(value = "/modifyP")
	public void modifyPrice(@RequestParam("idPanier") long id, @RequestParam("prixPanier") float prixtotal) {

		panierRepository.updatePrixPaniers(prixtotal, id);

	}

	@PostMapping("/newPanier")
	public Long newPanier(@RequestParam("clientId") String client,@RequestParam("totalPrice") double totalPrice) {
        Client c=clientRepository.findClientByIdClient(Long.parseLong(client));
		Panier p = new Panier(c,totalPrice);
		panierRepository.save(p);
		return p.getIdPanier();

	}

	@DeleteMapping(value = "/deletePanier/{id}")
	public void deletePanier(@PathVariable long id) {

		panierRepository.deletePanier(id);
	}

	@GetMapping("/maxId")
	public long maxIdPanier(){
		return panierRepository.getPnierMaxid();
	}

//	@PostMapping
//	public void addCart(@RequestBody Panier panier,ComposantePanier c) {
//		//@RequestBody because the client who's gonna create the cart that's why we take the request and mappit into the cart(panier)
//		
//		panierRepository.save(panier);
//		cpr.save(c); //still trying to know how to automatically save a composantepanier after saving a panier 
//	}
}
