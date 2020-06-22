package otg.sid.sec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import otg.sid.entities.appUser;
import otg.sid.service.accountService;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private accountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        appUser appUser = accountService.loadUSerByUsername(username);
        if(appUser==null) throw new UsernameNotFoundException("Username not found");
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        appUser.getRoles().forEach(r->{
            authorities.add(new SimpleGrantedAuthority(r.getRoleName()));
        });
        return new User(appUser.getUserName(),appUser.getPassword(),authorities);
    }
}
