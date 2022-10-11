package com.security.securitylession.repository;

import com.security.securitylession.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository  extends JpaRepository<Person,Integer> {
    // optional o demekdir ki nullda ola biler person.
    Optional<Person> findByUserName(String name);

    

}
