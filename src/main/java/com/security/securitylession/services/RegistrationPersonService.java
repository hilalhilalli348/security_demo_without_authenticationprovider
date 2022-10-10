package com.security.securitylession.services;

import com.security.securitylession.entity.Person;
import com.security.securitylession.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationPersonService {

    private PersonRepository personRepository;

    @Autowired
    public RegistrationPersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Transactional
    public void register(Person person){
        personRepository.save(person);
    }

}
