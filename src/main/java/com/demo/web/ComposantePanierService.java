package com.demo.web;

import java.util.ArrayList;
import java.util.List;

import com.demo.bo.productDashboard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dao.ComposantePanierRepository;
import com.demo.dao.PanierRepository;
import com.demo.dao.ProduitRepository;
import com.demo.entities.Categorie;
import com.demo.entities.ComposantePanier;
import com.demo.entities.Panier;
import com.demo.entities.Produit;


@RestController
@CrossOrigin(origins = "http://localhost:4000")
public class ComposantePanierService {

	@Autowired
	private ComposantePanierRepository composante;

	@Autowired
	private PanierService panierService;
	@Autowired
	private PanierRepository pr;
	@Autowired
	private ProduitRepository prd;

	private ArrayList<ComposantePanier> composantePaniers = new ArrayList<ComposantePanier>();
	private ArrayList<ComposantePanier> composantePanier = new ArrayList<ComposantePanier>();

	@GetMapping(value = "/Panier/ComposantePanier/{id}")
	public ComposantePanier getComposante(@PathVariable Long id) {
		return composante.findByIdComposante(id);
	}

	@GetMapping(value = "/ComposantePaniers")
	public List<ComposantePanier> getAllComposante() {
		return composante.findAll();
	}

	@GetMapping(value = "/Panier/ComposantePanier")
	public Long getProduitComposante(ComposantePanier c) {
		return composante.findProduitComposante(c.getIdComposante());
	}

	@GetMapping(value = "/Panier/ComposanteforPanier/{id}")
	public List<ComposantePanier> getPanierComposante(@PathVariable long id) {
		return composante.findPanierComposante(id);
	}

	/*
	 * @GetMapping(value="/ComposantePanierCommande/{id}") public
	 * List<ComposantePanier> getComposantePanierCommande(@PathVariable long id) {
	 * return composante.findPanierComposante(id); }
	 */
	@DeleteMapping(value = "/delPanier/{id}")
	public void deleteComposantePanier(@PathVariable long id) {
		composante.deleteComp(id);

		// panierService.modifiel(composante.findIdPanierComposant(id));

	}

	/*
	 * @PostMapping(value="/addPanier") public void addComposantePanier(@RequestBody
	 * ComposantePanier c) { composante.save(c); }
	 */

	@PostMapping("/addPanierPR")
	public ResponseEntity<ComposantePanier> addToCart(@RequestParam long idProduit, @RequestParam int quantite) {

		try {

			int flag = 0;
			composantePaniers.addAll(composante.findAll());
			for (ComposantePanier composantePanier : composantePaniers) {

				if (composantePanier.getProduit().getId() == idProduit) {

					composante.updQuantitComposantePanier(composantePanier.getQuantite() + 1,
							composantePanier.getIdComposante());
					flag = 1;
					break;
				}
			}
			if (flag == 1) {
				return new ResponseEntity<>(HttpStatus.OK);

			} else {

				// Panier panier = pr.findByIdPanier( (long)idPanier );

				Produit pro = prd.findByIdProduit((long) idProduit);
				composante.save(new ComposantePanier(pro, quantite));

				// pr.updatePrixPaniers(pro.getPrice(), idPanier);

				// panierService.modifiel((long)idPanier);

				return new ResponseEntity<>(HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping("/addPanier")
	public ResponseEntity<ComposantePanier> addComposantePanier(@RequestParam("idPanier") String idPanier,
			@RequestParam("idProduit") String idProduit, @RequestParam("quantite") String quantite) {
		System.out.println("noooooooooooo");
		System.out.println("idPanier"+idPanier+" idProsuit "+idProduit+" quanite "+quantite);




				Panier panier = pr.findByIdPanier(Long.parseLong(idPanier));
				Produit pro = prd.findByIdProduit(Long.parseLong(idProduit));
				System.out.println(panier+" "+pro);

				composante.save(new ComposantePanier(panier, pro, Integer.parseInt(quantite)));

				return new ResponseEntity<>(HttpStatus.OK);


	}

	@PutMapping("/modifyComposantPanierQuantite/{id}")
	public void updateQteProduit(@PathVariable long id, @RequestParam("quantite") int quantite) {

		composante.updQuantitComposantePanier(quantite, id);

		panierService.modifiel(composante.findIdPanierComposant(id));

	}

	@PutMapping("/modifyComposantPanierIdPq/{id}")
	public void updateIdPanier(@PathVariable long id, @RequestParam("idPanier") long idPanier) {

		composante.updtIdPanier(pr.findByIdPanier(idPanier), id);
		// panierService.modifiel(composante.findIdPanierComposant(id));
		float prixtotal = 0;
		composantePanier.addAll(composante.findPanierComposante(id));

		for (ComposantePanier composantePanier : composantePaniers) {

			prixtotal += composantePanier.getQuantite() * composantePanier.getProduit().getPrice();
		}

		pr.updatePrixPaniers(prixtotal, id);

	}


	@GetMapping("/getByProducts/{idProduct}")
	public List<productDashboard> getByProducts(@PathVariable("idProduct") long idProd){
		System.out.println(composante.getByProducts(idProd));
		return composante.getByProducts(idProd);
	}

}
