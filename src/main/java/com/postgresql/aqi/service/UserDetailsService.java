package com.postgresql.aqi.service;

import com.postgresql.aqi.exception.EmailAlreadyExistsException;
import com.postgresql.aqi.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDetailsService {
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    void registerUser(String username, String password, String email) throws EmailAlreadyExistsException;
}
