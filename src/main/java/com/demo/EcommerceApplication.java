package com.demo;

import com.demo.dao.*;
import com.demo.entities.Commande;
import com.demo.entities.ComposantePanier;
import com.demo.entities.Panier;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.mail.MessagingException;

import com.demo.web.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import com.demo.entities.Admin;
import com.demo.entities.Categorie;
import com.demo.entities.Produit;

import java.util.Timer;

@SpringBootApplication
public class EcommerceApplication {

	@Autowired
	PanierRepository panierRepo;
	@Autowired
	ComposantePanierRepository composanteRepo;
	@Autowired
	ProduitRepository produitRepo;
	@Autowired
	ClientRepository clientRepo;
	@Autowired
	CategorieRepository categorieRepo;
	@Autowired
	CommandeRepository commandeRepository;
	@Autowired
	PanierService panierService;

	@Autowired
	ClientService clientService;

//	ForgotPasswordController forgotPasswordController;

	@Autowired
	AdminRestService adminRestService;

	@Autowired
	CouponRepository couponRepository;

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private static Date d = new Date();

	@DateTimeFormat(pattern = "hh:mm")
	static Timer T = new Timer();
	@Autowired
	ProductRestService productRestService;

	public static String alphaNumericString(int len) {
		String AB = "0123456789";
		Random rnd = new Random();

		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		}
		return sb.toString();
	}

	public static void main(String[] args) throws MessagingException {
		ApplicationContext ctx = SpringApplication.run(EcommerceApplication.class, args);
		ProductRestService prser = ctx.getBean(ProductRestService.class);
		PanierRepository pn = ctx.getBean(PanierRepository.class);
		ProduitRepository pr = ctx.getBean(ProduitRepository.class);
		AdminRepository ar = ctx.getBean(AdminRepository.class);
		AdminRestService ad = ctx.getBean(AdminRestService.class);

		CategorieRepository cr = ctx.getBean(CategorieRepository.class);
		WishRepository wr = ctx.getBean(WishRepository.class);
		ComposantePanierRepository com = ctx.getBean(ComposantePanierRepository.class);
		ComposantePanierService co = ctx.getBean(ComposantePanierService.class);
		ClientService cs = ctx.getBean(ClientService.class);
		ClientRepository cre = ctx.getBean(ClientRepository.class);
		CouponRepository coupon = ctx.getBean(CouponRepository.class);
		PanierService ps = ctx.getBean(PanierService.class);
		CommandeRepository cmd = ctx.getBean(CommandeRepository.class);
		NotificationRestService not=ctx.getBean(NotificationRestService.class);
		pin t = ctx.getBean(pin.class);

		CouponService couponService = ctx.getBean(CouponService.class);

		CommandeRepository commandeRepository = ctx.getBean(CommandeRepository.class);

        //Add new admin
		//ad.newAdmin("name","email@exemple","password");
	}
}
