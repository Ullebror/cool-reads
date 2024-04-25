package fi.haagahelia.coolreads.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fi.haagahelia.coolreads.model.AppUser;
import fi.haagahelia.coolreads.repository.AppUserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	private final AppUserRepository repository;

	@Autowired
	public UserDetailsServiceImpl(AppUserRepository userRepository) {
		this.repository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Fetching the user from the repository
		AppUser curruser = repository.findByUsername(username);
		// Creating UserDetails object with user information
		UserDetails user = new org.springframework.security.core.userdetails.User(username, curruser.getPassword_hash(),
				AuthorityUtils.createAuthorityList(curruser.getRole()));
		return user;
	}
}