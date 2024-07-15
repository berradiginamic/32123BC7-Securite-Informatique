package fr.diginamic.webmvc01.services;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.diginamic.webmvc01.entities.User;
import fr.diginamic.webmvc01.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
	
	private final UserRepository userRepository;
	
	@Autowired
	public UserService(UserRepository userRepository) {
		// TODO Auto-generated constructor stub
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		Objects.requireNonNull(username);
		User user = userRepository.findUserWithName(username)
				.orElseThrow(()->new UsernameNotFoundException("user not found"));
		return user;
	}
}