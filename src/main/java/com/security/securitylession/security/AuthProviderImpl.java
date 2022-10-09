package com.security.securitylession.security;

import com.security.securitylession.services.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class AuthProviderImpl implements AuthenticationProvider {

    private final PersonDetailsService personDetailsService;

    @Autowired
    public AuthProviderImpl(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // securit form-dan gelen name-dir.
        String username = authentication.getName();
        System.out.println(username);
        UserDetails userDetails = personDetailsService.loadUserByUsername(username);

        // Credential - kimlik,ehliyyet demekdir. yeni password menasinda burda use edilir.
        String password = authentication.getCredentials().toString();


        if (!userDetails.getPassword().equals(password)){
                // bir basa yazmiriq ki,sifre sefdir
                // pis niyyetli hacker problemin tekce sifrede oldugunu bilmemelidir.
                throw new BadCredentialsException("ad ve ya sifre duzgun deyil");
        }

            return new UsernamePasswordAuthenticationToken(userDetails,password, Collections.emptyList());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
