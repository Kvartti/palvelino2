package palvelino.harjoitustyo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import palvelino.harjoitustyo.domain.User;
import palvelino.harjoitustyo.domain.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	private final UserRepository urepository;
	
	@Autowired
	public UserDetailServiceImpl(UserRepository userRepository) {
		this.urepository = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		User curuser = urepository.findByUsername(username);
		UserDetails user = new org.springframework.security.core.userdetails.User(username, curuser.getPasswordHash(), 
        		AuthorityUtils.createAuthorityList(curuser.getRole()));
        return user;
    }   
} 
