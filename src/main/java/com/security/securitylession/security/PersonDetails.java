package com.security.securitylession.security;

import com.security.securitylession.entity.Person;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

//UserDetails interfeyisi UserDetailsService ile birlikde
// isimizi asanlasdiran ve standartlasdiran bir interfeyisdir
// userler ucun.
// yeni onlari yazmayada bilerik.


//PersonDetails eyni zamanda principal adlanir.

public class PersonDetails implements UserDetails {

    private final Person person;

    public PersonDetails(Person person) {
        this.person = person;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return person.getPassword();
    }

    @Override
    public String getUsername() {
        return person.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Person getPerson(){
        return this.person;
    }
}
