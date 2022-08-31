package com.demo.web;

import java.awt.*;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dao.AdminRepository;
import com.demo.entities.Admin;
import com.demo.entities.Client;
import com.demo.entities.Panier;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "http://localhost:4000")
public class AdminRestService {
	@Autowired
	private AdminRepository admin;

	@Autowired
	private PasswordEncoder passwordEncoder;


	private static final Logger logger = LoggerFactory.getLogger("MyController.class");

	@GetMapping("/admins")
	public List<Admin> getAll() {
		return admin.findAll();
	}

	@GetMapping("/admin/{id}")
	public Admin getClient(@PathVariable long id) {
		return admin.findAdminById(id);
	}

	@PostMapping("/adminLogin")
	public ResponseEntity<Admin> signIn(@RequestParam(name = "email") String email,
			@RequestParam(name = "password") String password) {
		Admin a;
		try {
			a = admin.findAdminByEmail(email.replaceAll(" ", ""));
			boolean isPasswordMatch = passwordEncoder.matches(password, a.getPassword());

			if (isPasswordMatch) {
				System.out.println("exist");
				return new ResponseEntity<>(a, HttpStatus.OK);
			}

			else {
				System.out.println("no exist");
				return new ResponseEntity<>(HttpStatus.LOCKED);
			}
		} catch (NullPointerException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/newAdmin")
	public ResponseEntity<Admin> newAdmin(@RequestParam("pseudo") String name, @RequestParam("email") String email,
			@RequestParam("password") String password)

	{

		Admin a;

		try {
			int flag = 0;

			List<Admin> adminList = admin.findAll();

			for (Admin admin : adminList) {

				if (admin.getEmail().equals(email)) {

					flag = 1;

					break;
				}
			}
			if (flag == 1) {

				return new ResponseEntity<>(HttpStatus.LOCKED);

			} else {

				String mdp = passwordEncoder.encode(password);
				byte[] image=null;

				admin.save(new Admin(name, email, mdp,image));

				return new ResponseEntity<>(HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}


	@PostMapping("/updateProfilImage")
	@Transactional
	public ResponseEntity<Admin> updateProfileImage(@RequestParam("idAdmin") long id,@RequestParam("image") MultipartFile file,@RequestParam("pseudo") String pseudo,@RequestParam("email") String email ) throws IOException {
		   Admin Admin=admin.findAdminById(id);
		   if(!file.isEmpty()){

			   String fileName = StringUtils.cleanPath(file.getOriginalFilename());
			   logger.info("Name= " + fileName);
			   byte[] image = file.getBytes();
			   admin.updateImage(image,pseudo,email,id);
			   return new ResponseEntity<>(HttpStatus.OK);

		   }
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PutMapping(value = "/modifypasswordAdmin")
	public void updatePassworAdmin(@RequestParam("idAdmin") long id, @RequestParam("newPassword") String newPasword,
			@RequestParam("password") String passwordRessant) {
		Admin admins;
		admins = admin.findAdminById(id);

		boolean isPasswordMatch = passwordEncoder.matches(passwordRessant, admins.getPassword());

		if (isPasswordMatch) {

			String mdp = passwordEncoder.encode(newPasword);
			admin.updatePasswordAdmin(mdp, id);

		} else
			System.out.println(" error");

	}

}
