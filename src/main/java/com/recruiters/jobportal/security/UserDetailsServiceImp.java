package com.recruiters.jobportal.security;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.recruiters.jobportal.dao.UserDAO;
import com.recruiters.jobportal.model.UserDetail;

@Service("userDetailsService")
public class UserDetailsServiceImp implements UserDetailsService{

	@Autowired
	UserDAO userDAO;
	
	@Autowired
	private HttpSession session;
	
	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserDetail user = userDAO.findActiveUserByEmail(email);
		session.setAttribute("loggedInUserDetails", user);
	    UserBuilder builder = null;
	    if (user != null) {
	      
	      builder = org.springframework.security.core.userdetails.User.withUsername(email);
	      //builder.disabled(!user.isEnabled());
	      builder.password(user.getPassword());
	      String authorities = user.getRoleDetail().getRoleName();
	      builder.authorities(authorities);
	    } else {
	      throw new UsernameNotFoundException("User not found.");
	    }
	    return builder.build();
	}

}
