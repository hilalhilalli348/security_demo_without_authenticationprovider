package com.security.securitylession.services;

import com.security.securitylession.entity.Person;
import com.security.securitylession.repository.PersonRepository;
import com.security.securitylession.security.PersonDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

// biz bu PersonDetailsService sinifin "AuthPrroviderImpl implements AuthenticationProvider" - da
// public Authentication authenticate(Authentication authentication) metodunda istifade edeceyik

//UserDetailsService interfeyisi UserDetails ile birlikde
// isimizi asanlasdiran ve standartlasdiran bir interfeyisdir
// userler ucun.
// yeni onlari yazmayada bilerik.
@Service
public class PersonDetailsService implements UserDetailsService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonDetailsService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Person> person = personRepository.findByUserName(username);

        if (person.isEmpty()) {
            // bele user olmasa security form-da gosterilecek.
            throw new UsernameNotFoundException("bele user yoxdur");
        }

        return new PersonDetails(person.get());

    }
}
