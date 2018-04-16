package inci.dashboard.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import inci.entities.User;
import inci.repositories.UsersRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	User user = usersRepository.findByEmail(email);
	if (user != null) {
	    Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
	    grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
	    return new inci.entities.security.core.userdetails.User(user.getEmail(), user.getPassword(),
		    grantedAuthorities);
	} else {
	    throw new UsernameNotFoundException("User not found");
	}
    }
}
