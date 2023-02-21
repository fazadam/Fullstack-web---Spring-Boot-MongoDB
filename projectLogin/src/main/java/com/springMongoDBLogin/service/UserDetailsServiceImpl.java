package com.springMongoDBLogin.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springMongoDBLogin.domain.Role;
import com.springMongoDBLogin.domain.User;
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

	Role roleUser = new Role("ROLE_USER");
	Role roleAdmin = new Role("ROLE_ADMIN");
	Set<Role> roles = new HashSet<>();

	// regisztracio
	public User registerUser(User userToRegister) {
		User userIsInDB = userRepository.findByUsername(userToRegister.getUsername());

		// ha nincs benne a DBben ->regisztralja
		if (userIsInDB == null) {

			// ujonnan regisztralt felhasznalo kapja meg a ROLE_USER-t automatikusan
			if (roleRepository.findByName("ROLE_USER") == null) {
				System.out.println(roleUser);
				roleRepository.save(roleUser);
				roles.add(roleUser);
				userToRegister.setRoles(roles);
			} else {
				roles.add(roleUser);
				//roles.add(roleAdmin); //--> ha ket rolet kap az uj user is mukodik a UserPage es az AdminPage-re is
				userToRegister.setRoles(roles);
			}

			// pw encoding w/ bcryptencoder --> securityConfigban van a bean
			userToRegister.setPassword(encoder.encode(userToRegister.getPassword()));

			return userRepository.save(userToRegister);

		} else {

			throw new RuntimeException("mar letezik a User az adatbazisban ezzel a nevvel");

		}
	}

	// login 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);

		if (user == null)
			throw new UsernameNotFoundException("User with username - " + username + " not found");
		return UserDetailsImpl.build(user);
	}

}
