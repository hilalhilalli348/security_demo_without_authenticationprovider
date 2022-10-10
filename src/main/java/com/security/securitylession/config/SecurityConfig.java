package com.security.securitylession.config;

import com.security.securitylession.services.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {



    private PersonDetailsService personDetailsService;

    @Autowired
    SecurityConfig(PersonDetailsService personDetailsService){
        this.personDetailsService=personDetailsService;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().
                         authorizeRequests()
                        .antMatchers("/auth/login-page","/error").permitAll()
                        .anyRequest().authenticated()
                        .and()
                        .formLogin().loginPage("/auth/login-page")
                        .loginProcessingUrl("/process_login")
                        .defaultSuccessUrl("/home",true )
                        .failureUrl("/auth/login-page?error");

    }

    // mes bu metod authentication edir userleri.
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // burada ariq login parolu spring security ozu yoxlayacaq.
         auth.userDetailsService(personDetailsService);

    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        // sifreleme use etmediyimizi bildiririk
        // bunu passowrda {noop} yazaraqda deye bilerik.
        return NoOpPasswordEncoder.getInstance();
    }
}
