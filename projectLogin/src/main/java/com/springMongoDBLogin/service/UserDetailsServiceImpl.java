package com.springMongoDBLogin.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//import com.springMongoDBLogin.config.CustomAuthenticationProvider;
import com.springMongoDBLogin.domain.Role;
import com.springMongoDBLogin.domain.User;
import com.springMongoDBLogin.domain.cardGame.Card;
import com.springMongoDBLogin.repository.CardRepository;
import com.springMongoDBLogin.repository.RoleRepository;
import com.springMongoDBLogin.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private CardService cardservice;
	@Autowired
	private CardRepository cardRepository;

	Role roleUser = new Role("ROLE_USER");
	Role roleAdmin = new Role("ROLE_ADMIN");
	Set<Role> roles = new HashSet<>();

	// regisztracio
	public User registerUser(User userToRegister) throws IOException {
		// ha nincs benne a DBben ->regisztralja
		if (userRepository.findByUsername(userToRegister.getUsername()) != null) {
			throw new RuntimeException("Username already taken: " + userToRegister.getUsername());
		} else if (userRepository.findByEmail(userToRegister.getEmail()) != null) {
			throw new RuntimeException("Email is already taken: " + userToRegister.getEmail());
		} else {

			// ha uj user regisztral automatikusan megkapja a ROLE_USER-t
			if (roleRepository.findByName("ROLE_USER") == null) {
				/* System.out.println(roleUser); */
				roleRepository.save(roleUser);
				roles.add(roleUser);
				userToRegister.setRoles(roles);
			} else {
				roles.add(roleUser);
				// roles.add(roleAdmin); //--> ha ket rolet kap az uj user is mukodik a UserPage
				// es az AdminPage-re is
				userToRegister.setRoles(roles);
			}

			// nezze meg h van e uj kartya a cardservice-startingdeckben es tegye bele a
			// cardrepoba ha van
			// card repo minden kartyajat adja meg az uj usernek
			// kesobb lehet h csak mondjuk nem mindet csak bizonyos kezdot pl 20at?
			cardservice.addNewCardsToTheRepo();
			userToRegister.setAllCards(cardRepository.findAll());

			// pw encoding w/ bcryptencoder --> securityConfigban van a bean
			userToRegister.setPassword(encoder.encode(userToRegister.getPassword()));

			// videoset
			userToRegister.setFavouriteVideos(new HashSet<>());

			return userRepository.save(userToRegister);
		}
	}

//	// login
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);

		if (user == null)
			throw new UsernameNotFoundException("User with username - " + username + " not found");

		try {
			cardservice.addNewCardsToTheRepo();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// van-e uj kartya a cardrepoban miota regisztralt?
		List<Card> allCardsInTheRepo = cardRepository.findAll();
		for (Card repoCard : allCardsInTheRepo) {
			boolean userHasThisCard = false;
			for (Card userCard : user.getAllCards()) {
				if (repoCard.getName().equals(userCard.getName())) {
					userHasThisCard = true;
					break;
				}
			}
			if (!userHasThisCard) {
				user.getAllCards().add(repoCard);
				userRepository.save(user);
			}
		}

		return user;
	}

	// minden kartya kikerese
	public List<Card> printAllCardsOfTheUser(String username) {

		User user = userRepository.findByUsername(username);
		if (user == null)
			throw new UsernameNotFoundException("User with username - " + username + " not found");

		return user.getAllCards();
	}

	// user favourite videos
	public void setFavouriteVideos(String username, String favouriteVideosUrl) {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new RuntimeException("first login to add favorite videos");
		}
		user.getFavouriteVideos().add(favouriteVideosUrl);
		userRepository.save(user);

	}

	public Set<String> getFavouriteVideos(String username) {
		return userRepository.findByUsername(username).getFavouriteVideos();
	}

	public void removeFavoriteVideos(String username, String favouriteVideosUrl) {
		User user = userRepository.findByUsername(username);
		user.getFavouriteVideos().remove(favouriteVideosUrl);
		userRepository.save(user);
	}

	// user profil udpatelese
	public User updateUserData(String username, User userToUpdate) {
		User user = userRepository.findByUsername(username); // loggedInUser session nevevel keresse ki

		if (user == null) {
			throw new IllegalArgumentException("User with username " + username + " not found");
		}

		// userToUpdate.setPassword(encoder.encode(userToUpdate.getPassword()));

		// Check if password is not empty and different from current password
		if (userToUpdate.getPassword() != null && !userToUpdate.getPassword().isEmpty()
				&& !encoder.matches(userToUpdate.getPassword(), user.getPassword())) {
			userToUpdate.setPassword(encoder.encode(userToUpdate.getPassword()));
		} else {
			userToUpdate.setPassword(user.getPassword());
		}

		user.setPassword(userToUpdate.getPassword());

//		// vesse ossze a db-ben levo jelszoval, ha megegyezik -> Error =
//		if (encoder.matches(userToUpdate.getPassword(), user.getPassword())) {
//			throw new RuntimeException("choose different password" + " "
//					+ encoder.matches(userToUpdate.getPassword(), user.getPassword()));
//		} else {
//			userToUpdate.setPassword(encoder.encode(userToUpdate.getPassword()));
//			user.setPassword(userToUpdate.getPassword());
//		}

		// letezik e mar az uj username
		if (userToUpdate.getUsername() != null && !userToUpdate.getUsername().equals(user.getUsername())) {
			User existingUserWithUsername = userRepository.findByUsername(userToUpdate.getUsername()); // uj username
																										// letezik-e mar
																										// a DB-ben
			if (existingUserWithUsername != null) { // ha az uj username mar
													// foglalt es nem a
													// jelenlegi userhez
													// tartozik
				throw new RuntimeException("Username already exists");
			}
			user.setUsername(userToUpdate.getUsername());
		}

		// foglalt-e mar a beirt email
		if (userToUpdate.getEmail() != null && !userToUpdate.getEmail().equals(user.getEmail())) {
			User existingUserWithEmail = userRepository.findByEmail(userToUpdate.getEmail());
			if (existingUserWithEmail != null) {
				throw new RuntimeException("Email already exists");
			}
			user.setEmail(userToUpdate.getEmail());
		}

		userRepository.save(user);

		return user;
	}

	// csak username update
	public void updateProfileUsername(String username, String newUsername) {
		User user = userRepository.findByUsername(username); // loggedInUser session nevevel keresse ki

		if (newUsername != null && !newUsername.equals(user.getUsername())) {
			User existingUserWithUsername = userRepository.findByUsername(newUsername); // uj username
																						// letezik-e mar
																						// a DB-ben
			if (existingUserWithUsername != null) { // ha az uj username mar
													// foglalt es nem a
													// jelenlegi userhez
													// tartozik
				throw new RuntimeException("Username already exists");
			}
			user.setUsername(newUsername);
		}
		userRepository.save(user);
	}

	// csak email update
	public void updateProfileEmail(String username, String newEmail) {
		User user = userRepository.findByUsername(username); // loggedInUser session nevevel keresse ki

		// foglalt-e mar a beirt email
		if (newEmail != null && !newEmail.equals(user.getEmail())) {
			User existingUserWithEmail = userRepository.findByEmail(newEmail);
			if (existingUserWithEmail != null) {
				throw new RuntimeException("Email already exists");
			}
			user.setEmail(newEmail);
		}
		userRepository.save(user);
	}

	// csak password update
	public void updateProfilePassword(String username, String newPassword) {
		User user = userRepository.findByUsername(username); // loggedInUser session nevevel keresse ki

		if (newPassword == null || newPassword.isEmpty() || encoder.matches(newPassword, user.getPassword())) {
			throw new RuntimeException("choose different pw not the current one");
		}

		user.setPassword(encoder.encode(newPassword));
		userRepository.save(user);

	}

}
