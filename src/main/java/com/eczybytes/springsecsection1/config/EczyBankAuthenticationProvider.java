package com.eczybytes.springsecsection1.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EczyBankAuthenticationProvider implements AuthenticationProvider {

    private final EczyBankUserDetailService eczyBankUserDetailService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserDetails userFromDB = eczyBankUserDetailService.loadUserByUsername(username);

        if (passwordEncoder.matches(password, userFromDB.getPassword())) {
//            System.out.println(userFromDB.getUsername() + " User is loaded from databases ");
            return  new UsernamePasswordAuthenticationToken(username, password, userFromDB.getAuthorities());
        }else {
            throw new BadCredentialsException("Bad credentials");
        }
    }
    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
