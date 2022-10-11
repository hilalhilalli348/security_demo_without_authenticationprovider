package com.security.securitylession.services;

import com.security.securitylession.entity.Person;
import com.security.securitylession.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationPersonService {

    private PersonRepository personRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationPersonService(PersonRepository personRepository,PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.passwordEncoder=passwordEncoder;
    }

    @Transactional
    public void register(Person person){
        // burada person-dan aciq sifreni alib sifreliyib set edirik
        // elede db-ye save edilir.
        person.setPassword(passwordEncoder.encode( person.getPassword()));
        personRepository.save(person);
    }

}
