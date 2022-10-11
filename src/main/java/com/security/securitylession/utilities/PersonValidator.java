package com.security.securitylession.utilities;

import com.security.securitylession.entity.Person;
import com.security.securitylession.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;
@Component
// bu validatoru use etmek isdediyimiz yerde inject edirik.
public class PersonValidator implements Validator {

    private PersonRepository personRepository;

    @Autowired
    public PersonValidator(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        // yeni ancaq Person sinifi ucundur ancaq onu destekleyir.
        // diger entiti leri desteklemeycek ancaq Person-i destekleyecek
        // bele yazanda.
        return Person.class.equals(clazz);
    }

    //Person @Valid edilenden sonra biz Personu bu metoda gondereceyik.
    // burda validden kececek.
    @Override
    public void validate(Object target, Errors errors) {
        // errors -  bas veren xetalari saxalayacaq obyektidir
        // xetalari biz ona elave edirik.
        // o bindingResult-i temsil edir..

        // validate prosessi burada aparilir.
        // target bizim Person obyekti olacaq.
        Person person = (Person) target;

        // 1. burada biz @Valid edilen Person-in DB-de eynisinin olub olmadigin
        // yoxlayaq. bunun ucunda personRepository-ni inject edirik
        // sonra @Valid edilen Person-i aliriq(target) yoxlamadan kecirdirik.


        // sade olmasi ucun ada gore apardiq amma uniqe deyere gore
        // muqaise aparilmalidir!
        Optional<Person> person1 = personRepository.findByUserName(person.getUserName());

        //if (person1!=null)  belede olardi amma artiq Optional sinifi daha oxunaqli oldugu ucun
        // o use edilir , isPresent() yeni varsa null deyilse.

        if (person1.isPresent()) {
            // bele adli user olduguna gore xeta vermelidir.
            // 1-ci parametr xeta bas veren fieldi temsil edir.
            // 2-ci xeta codu ,yazmasaqda olar.
            // 3-xeta mesaji
            // userName - burada Person-in uygun field adidir.mutleq field adi ile eyni olmalidir
            // eks halda xeta alariq.
            // eyni zamanda thymeleaf-de de .
            errors.rejectValue("userName", "", "ad duzgun daxil edilmeyib");
        }

    }
}
