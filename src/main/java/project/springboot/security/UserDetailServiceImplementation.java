package project.springboot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import project.springboot.model.Administrator;
import project.springboot.repository.AdministratorRepository;

@Service
public class UserDetailServiceImplementation implements UserDetailsService{

	@Autowired
	private AdministratorRepository administratorRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Administrator systemUser =  administratorRepository.findUserByLogin(username);
		
		if (systemUser == null) {
			throw new UsernameNotFoundException("System User Not Found!");
		}
		
		return systemUser;
	}

}
