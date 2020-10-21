package project.springboot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.springboot.model.Administrator;
import project.springboot.repository.AdministratorRepository;

@Service
@Transactional
public class UserDetailServiceImplementation implements UserDetailsService{

	@Autowired
	private AdministratorRepository administratorRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Administrator systemUser =  administratorRepository.findUserByLogin(username);
		
		if (systemUser == null) {
			throw new UsernameNotFoundException("System User Not Found!");
		}
		
		return new User(systemUser.getLogin(),        //usename
						systemUser.getPassword(),     //password
						true,                         //enabled
						true,                         //accountNonExpired
						true,                         //credentialsNonExpired
						true,                         //accountNonLocked
						systemUser.getAuthorities()); //authorities
	}

}
