package com.testing.ground.jwt;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    // Example in-memory users; replace with DB call or repository
    private static final Map<String, UserDetails> users = Map.of(
            "amigaura", User.withUsername("amigaura")
                    .password("{noop}secret")  // {noop} means no encoding, only for demo
                    .authorities(List.of(new SimpleGrantedAuthority("ROLE_USER")))
                    .build(),

            "admin", User.withUsername("admin")
                    .password("{noop}adminpass")
                    .authorities(List.of(new SimpleGrantedAuthority("ROLE_ADMIN")))
                    .build()
    );

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = users.get(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        return user;
    }
}

