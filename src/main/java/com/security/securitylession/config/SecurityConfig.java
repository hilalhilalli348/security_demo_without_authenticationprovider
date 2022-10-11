package com.security.securitylession.config;

import com.security.securitylession.services.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.ldap.EmbeddedLdapServerContextSourceFactoryBean;
import org.springframework.security.config.ldap.LdapBindAuthenticationManagerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

//@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {



    private PersonDetailsService personDetailsService;

    @Autowired
    SecurityConfig(PersonDetailsService personDetailsService){
        this.personDetailsService=personDetailsService;
    }


    // ilk http sorgusunun geldiyi yer SecurityFilterChain-dir
    // gelen sorgunu HttpSecurity http aliriq.

    // bu metod  autorization-i temsil edir.
    // yeni autorization-dan bura mesuliyyet dasiyir.
    @Override
    protected void configure(HttpSecurity http) throws Exception {

                       http.
                         authorizeRequests()
                        .antMatchers("/auth/login-page","/error","/auth/register").permitAll()
                        .anyRequest().authenticated()

                        .and()
                        .formLogin().loginPage("/auth/login-page")
                        .loginProcessingUrl("/process_login")
                        .defaultSuccessUrl("/home",true )
                        .failureUrl("/auth/login-page?error")

                        .and()
                        .logout().logoutUrl("/logout")
                        .logoutSuccessUrl("/auth/login-page");
    }







    // bura ise   authentication-i temsil edir.
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // burada ariq login parolu spring security ozu yoxlayacaq.
         auth.userDetailsService(personDetailsService)
                 .passwordEncoder(getPasswordEncoder());

    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        // burada passwordencoder-i yaradiriq ,biz burda BCryptPasswordEncoder use edeceyimizi
        // bildiririk
        // harda BCryptPasswordEncoder use etmek isdiyirikse orda inject edirik
        // BCryptPasswordEncoder ve use edririk.
        return new BCryptPasswordEncoder();
    }
}
