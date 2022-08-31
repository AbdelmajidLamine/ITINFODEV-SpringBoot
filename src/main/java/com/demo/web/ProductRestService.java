package com.demo.web;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

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
import org.springframework.web.bind.annotation.ModelAttribute;

import com.demo.dao.CategorieRepository;
import com.demo.dao.ProduitRepository;
import com.demo.entities.Categorie;
import com.demo.entities.Produit;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.Files.copy;
import static java.nio.file.Paths.get;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;
import org.springframework.core.io.ResourceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@RestController
@CrossOrigin(origins = "http://localhost:4000")
public class ProductRestService {
	@Autowired
	private ProduitRepository produits;

	@Autowired
	private CategorieRepository cr;
	@Autowired
	ResourceLoader resourceLoader;
	private static final Logger logger = LoggerFactory.getLogger("MyController.class");

	// private final Logger log = LoggerFactory.getLogger(this.getClass());

	public static final String DIRECTORY = "src/main/resources/FilesUploaded";

	@GetMapping(value = "/products")
	public List<Produit> getAll() {
		return produits.findAll();
	}

	@GetMapping(value = "/chercheProduct/{mot}")
	public List<Produit> chercheProduit(@PathVariable String mot) {
		return produits.search(mot);

	}

	@GetMapping("/shop-grid-standard")
	public ResponseEntity<Map<String, Object>> getAllProducts(@RequestParam(required = false) String title,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {

		try {
			List<Produit> produit = new ArrayList<Produit>();
			Pageable paging = PageRequest.of(page, size);

			Page<Produit> pageTuts;

			pageTuts = produits.findAll(paging);

			produit = pageTuts.getContent();

			Map<String, Object> response = new HashMap<>();
			response.put("produits", produit);
			response.put("currentPage", pageTuts.getNumber());
			response.put("totalItems", pageTuts.getTotalElements());
			response.put("totalPages", pageTuts.getTotalPages());


			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/product/{id}")
	public Produit getProduit(@PathVariable long id) {
		return produits.findByIdProduit(id);
	}

	@GetMapping("/products/{nomCategory}")
	public List<Produit> getProduitByCategorie(@PathVariable String nomCategory) {
		return produits.findByCategory(nomCategory);
	}

	@PostMapping("/addProduct")
	public ResponseEntity<Produit> newProduit(@RequestParam("name") String name,
			@RequestParam("shortDescription") String shortDescription, @RequestParam("price") float price,
			@RequestParam("stock") int stock, @RequestParam("image") MultipartFile file,
			@RequestParam("idCategorie") long idCategorie) throws IOException {

		try {
			System.out.println("AAAAAA");

			// System.out.println(file);
			String fileName = StringUtils.cleanPath(file.getOriginalFilename());
			logger.info("Name= " + fileName);
			
			byte[] image = file.getBytes();
			Produit p = new Produit();
			p.setImage(image);
			Categorie ctgry = cr.findByIdCategorie(idCategorie);
			p.setName(name);
			p.setCategory(ctgry);
			p.setPrice(price);
			p.setStock(stock);
			// p.setImage(file);
			p.setShortDescription(shortDescription);
			produits.save(p);
			
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	/*
	 * public ResponseEntity<Produit> newProduit(@RequestParam ("name") String name,
	 * 
	 * @RequestParam ("shortDescription") String shortDescription
	 * , @RequestParam("price") float price ,@RequestParam ("stock") int stock
	 * , @RequestParam ("image")String image ,@RequestParam ("idCategorie") long
	 * idCategorie) { try { Categorie ctgry = cr.findByIdCategorie(idCategorie);
	 * produits.save( new Produit(name, shortDescription, price, stock ,true, image,
	 * ctgry) ); return new ResponseEntity<>(HttpStatus.OK); } catch (Exception e) {
	 * return new ResponseEntity<>(HttpStatus.NOT_FOUND); }
	 * 
	 * }
	 */

	@GetMapping("/removeProduct/{id}")
	public void deleteProduit(@PathVariable Long id) {
		produits.deleteById(id);
	}

	@PutMapping(value = "/modifyProductPrice")
	public void updatePrixProduit(@RequestParam("id") long id, @RequestParam("price") float price) {
		produits.updatePrixProduit(price, id);
	}

	@PutMapping(value = "/modifyProdutQuantite")
	public void updateQteProduit(@RequestParam("id") long id, @RequestParam("stock") int stock) {
		produits.updateQuantiteProduit(stock, id);

	}

	@PutMapping("/products/{id}")
	public ResponseEntity<Produit> updateProduit(@PathVariable Long id, @RequestParam("name") String name,@RequestParam("shortDescription") String shortDescription,@RequestParam("status") boolean status,@RequestParam("stock") int stock,@RequestParam("image") MultipartFile file,@RequestParam("price") float price,@RequestParam("category") String category) throws IOException {
		
		Produit produit = produits.findByIdProduit(id);
		// .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id
		// :" + id));
		byte[] image = file.getBytes();

		if (name != null) {

			produit.setName(name);
		}

		if (image != null) {
			produit.setImage(image);
		}
		if (shortDescription != null) {
			produit.setShortDescription(shortDescription);
		}
		produit.setStatus(status);

		if (stock!=0) {
			produit.setStock(stock);
		}
		if(price!=0){
			produit.setPrice(price);
		}
      System.out.println(category);
		produit.setCategory(cr.findByIdCategorie(Long.parseLong(category)));


		Produit updateProduit = produits.save(produit);
		return ResponseEntity.ok(updateProduit);
	}
}
