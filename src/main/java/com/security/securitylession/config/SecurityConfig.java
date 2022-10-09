package com.security.securitylession.config;

import com.security.securitylession.security.AuthProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFilter;

//@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private AuthProviderImpl authPrrovider;

    @Autowired
    public void SecurityConfig(AuthProviderImpl authPrrovider) {
        this.authPrrovider = authPrrovider;
    }

    // mes bu metod authentication edir userleri.
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authPrrovider);
    }
}
