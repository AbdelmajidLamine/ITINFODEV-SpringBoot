package com.demo.web;

import java.io.IOException;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.demo.entities.Admin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dao.ClientRepository;
import com.demo.dao.PanierRepository;
import com.demo.entities.Client;
import com.demo.entities.Panier;
import com.demo.entities.Produit;
import com.paypal.base.codec.CharEncoding;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "http://localhost:4000")

public class ClientService {
	@Autowired
	private ClientRepository client;
	@Autowired
	private PanierRepository panierRepository;

	@Autowired
	private pin p;
	// @Autowired
	// private BCryptPasswordEncoder bCryptPasswordEncoder;

	private static final Logger logger = LoggerFactory.getLogger("MyController.class");

	@GetMapping(value = "/client")
	public List<Client> getAll() {
		return client.findAll();
	}

	@GetMapping("/client/{id}")
	public Client getClient(@PathVariable long id) {
		return client.findClientByIdClient(id);
	}

	@PutMapping(value = "/modifypasswordClient")
	public void updatePasswordClient(@RequestParam("id") long id, @RequestParam("password") String password,
			@RequestParam("password") String passwordRessant) {
		Client a;
		a = client.findClientByIdClient(id);

		boolean isPasswordMatch = passwordEncoder.matches(passwordRessant, a.getPassword());
		if (isPasswordMatch) {

			String mdp = passwordEncoder.encode(password);
			client.updatePasswordClient(mdp, id);

		} else
			System.out.println(" error");

	}

	@PostMapping("/SignIn")
	public ResponseEntity<Client> signIn(@RequestParam(name = "email") String email,
			@RequestParam(name = "password") String password) {

		Client a;

		try {

			a = client.findClientByEmail(email.replaceAll(" ", " "));
			boolean isPasswordMatch = passwordEncoder.matches(password, a.getPassword());

			if (isPasswordMatch) {

				return new ResponseEntity<>(a, HttpStatus.OK);
			} else {

				return new ResponseEntity<>(HttpStatus.LOCKED);
			}

		} catch (NullPointerException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}



	@PostMapping("/updateProfilClient")
	@Transactional
	public ResponseEntity<Admin> updateProfileImage(@RequestParam("id") long id, @RequestParam("image") MultipartFile file, @RequestParam("name") String name, @RequestParam("email") String email ,@RequestParam("address") String Address,@RequestParam("tel") String tel) throws IOException {
		Client cl=client.findClientByIdClient(id);
		if(!file.isEmpty()){

			String fileName = StringUtils.cleanPath(file.getOriginalFilename());
			logger.info("Name= " + fileName);
			byte[] image = file.getBytes();
			client.updateImageClient(image,name,email,Address,tel,id);
			return new ResponseEntity<>(HttpStatus.OK);

		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/SignUp")
	public ResponseEntity<Client> SignUp(@RequestParam("name") String name, @RequestParam("email") String email,
			@RequestParam("password") String password)

	{

		Client a;

		try {
			int flag = 0;

			List<Client> userList = client.findAll();

			for (Client user : userList) {

				if (user.getEmail().equals(email)) {

					flag = 1;
					break;
				}
			}
			if (flag == 1) {
				return new ResponseEntity<>(HttpStatus.LOCKED);

			} else {

				/// Panier p= panierRepository.save(new Panier());
				String mdp = passwordEncoder.encode(password);
				Client c = client.save(new Client(name, email, mdp));

				client.updatePanierClient(panierRepository.save(new Panier(c)), c.getIdClient());

				return new ResponseEntity<>(HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	public static String alphaNumericString(int len) {
		String AB = "0123456789";
		Random rnd = new Random();

		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		}
		return sb.toString();
	}

	// @PutMapping (value ="/modifyCodePinClient")
	public void updateResetPasswordCodePin(String codePin, String email) throws UserPrincipalNotFoundException {

		Client clien = client.findClientByEmail(email);

		if (clien != null) {
			clien.setCodePin(codePin);

			client.save(clien);

		} else {
			throw new UserPrincipalNotFoundException("Could not find any client with the email " + email);
		}
	}

	public Client getByResetPasswordCodePin(String codePain) {

		return client.findByResetPasswordCodePin(codePain);

	}

	@PostMapping(value = "/newPassword")
	public ResponseEntity<Client> updatePassword(@RequestParam("email") String email,
			@RequestParam("password") String password) {

		Client c = client.findClientByEmail(email);
		try {
			if (c == null) {

				return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
			}

			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String encodedPassword = passwordEncoder.encode(password);

			c.setPassword(encodedPassword);

			c.setCodePin(null);
			client.save(c);
			return new ResponseEntity<>(c, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping(path = "/forgotPasword")
	public ResponseEntity<Client> Search(@RequestParam(name = "email") String email) {

		if (client.findClientByEmail(email) == null) {

			return new ResponseEntity<>(client.findClientByEmail(email), HttpStatus.LOCKED);
		}

		String codepin = alphaNumericString(5);

		try {
			updateResetPasswordCodePin(codepin, email);
		} catch (UserPrincipalNotFoundException e) {

			e.printStackTrace();
		}

		try {
			p.Pin(email, codepin);

		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ResponseEntity<>(client.findClientByEmail(email), HttpStatus.OK);

	}
//////////////////////////////////

	@PostMapping(path = "/virifieCodPin")
	public ResponseEntity<Client> VirifieCodPin(@RequestParam("email") String email,
			@RequestParam("codePin") String pin) {

		Client c = client.findClientByEmail(email);

		if (c == null) {

			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

		} else if (!c.getCodePin().equals(pin)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		else {

			return new ResponseEntity<>(c, HttpStatus.OK);
		}

	}

}
