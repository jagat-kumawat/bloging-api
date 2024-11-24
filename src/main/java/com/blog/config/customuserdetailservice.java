package com.blog.config;

import com.blog.entity.user;
import com.blog.exception.runexception;
import com.blog.repo.userrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class customuserdetailservice implements UserDetailsService {
    @Autowired
    private userrepo ur;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        user userid = this.ur.findByEmail(username).orElseThrow(() -> new runexception("userid", "uservalue" + username, 0));
        return userid;
    }
}
