package akhafiz.services.impl;

import akhafiz.model.HostUser;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service("hostUserDetailsService")
public class HostUserDetailsServiceImpl implements UserDetailsService {

    private static final Map<String, HostUser> USER_MAP = new HashMap<>() {
        {
            put("admin", new HostUser("admin", "admin", Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"))));
        }
    };

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        HostUser user = USER_MAP.get(s);

        if (user == null) {
            throw new UsernameNotFoundException(s + "not found");
        }

        return user;
    }
}
