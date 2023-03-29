package com.springMongoDBLogin.service;


import java.io.IOException;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springMongoDBLogin.domain.User;

@Service
public interface UserDetailsService {
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, IOException;

}
