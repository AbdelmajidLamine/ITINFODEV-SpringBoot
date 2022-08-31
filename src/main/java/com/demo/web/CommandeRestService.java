package com.demo.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.dao.ProduitRepository;
import com.demo.entities.Categorie;
import com.demo.entities.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.demo.dao.ClientRepository;
import com.demo.dao.CommandeRepository;
import com.demo.dao.ComposantePanierRepository;
import com.demo.entities.Client;
import com.demo.entities.Commande;
import com.demo.entities.ComposantePanier;

@RestController
@CrossOrigin(origins = "http://localhost:4000")
public class CommandeRestService {

	@Autowired
	CommandeRepository cr;
	@Autowired
	ProduitRepository pr;
	@Autowired
	ComposantePanierRepository composantePanierRepository;

	@GetMapping(value = "/commande/{id}")
	public Commande commandeById(@PathVariable long id) {
		Commande commande = cr.findCommandeByID(id);
		return commande;

	}

	@GetMapping(value = "/commandess/{mail}")
	public List<Commande> commandesByEmail(@PathVariable String mail) {
         System.out.println(cr.findByEmailCommande(mail));
		return cr.findByEmailCommande(mail);
	}
	@GetMapping("/commandesTotalPrice")
	public double commandesTotalPrice(){
			List<Commande> commandes=cr.findAll();
			double somme=0;
			for(int i=0;i<commandes.size();i++){
				 somme+=commandes.get(i).getTotalPrice();
			}
			return Math.round(somme*100.0)/100.0;
	}

	@GetMapping("/livraisonCommande/{id}")
	public String findByLivraisonCommande(@PathVariable("id") long idCommande){
		return cr.findByLivraisonCommande(idCommande);
	}

	@GetMapping(value = "/commandes")
	public List<Commande> commandes() {
		return cr.findAll();
	}

	@PostMapping("/saveCommande")
	public ResponseEntity<Produit> saveCommande(@RequestParam("firstName") String firstName,
			@RequestParam("companyName") String companyName, @RequestParam("adresse") String adresse,
			@RequestParam("adresse_2") String adresse_2, @RequestParam("city") String city,
			@RequestParam("lastName") String lastName, @RequestParam("phone") String phone,
			@RequestParam("mail") String mail, @RequestParam("codePostal") String codePostal,
			@RequestParam("notes") String notes, @RequestParam("totalPrice") String totalPrice,
			@RequestParam("country") String country,
			@RequestParam("drive") @DateTimeFormat(pattern = "yyyy-MM-dd") Date drive,
			@RequestParam("time") @DateTimeFormat(pattern = "HH:mm") Date heurRamassage,
			@RequestParam("idNewPanier") String idNewPanier) {

		try {

			List<ComposantePanier> comp = composantePanierRepository.findPanierComposante(Long.parseLong(idNewPanier));
            String livraison="En attendant";
			Commande commande = new Commande(firstName, lastName, companyName, adresse, adresse_2, country, city, phone,
					mail, codePostal, notes, drive, heurRamassage, Double.parseDouble(totalPrice), comp,livraison);

			cr.save(commande);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping(value = "/modifyLivraisonCommand/{id}")
	public void modifieLivraisonCommand(@PathVariable long id) {
		cr.livreCommande("livré", id);
	}

	@GetMapping("/deleteOrder/{id}")
	public void deleteOrder(@PathVariable Long id){
		cr.deleteById(id);
	}

}
