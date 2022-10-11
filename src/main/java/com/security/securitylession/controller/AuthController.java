package com.security.securitylession.controller;

import com.security.securitylession.entity.Person;
import com.security.securitylession.services.RegistrationPersonService;
import com.security.securitylession.utilities.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private RegistrationPersonService registrationPersonService;
    private PersonValidator personValidator;
    @Autowired
    public AuthController(RegistrationPersonService registrationPersonService,PersonValidator personValidator) {
        this.registrationPersonService = registrationPersonService;
        this.personValidator = personValidator;
    }

    @GetMapping("/login-page")
    public String login() {

        return "login";
    }

    @GetMapping("/register")
    public String getRegister(Model model) {
        model.addAttribute("person",new Person());
        return "register";
    }


    // burada @Valid edende ,entiti-de yazilan annotationlardaki sertler odenmeyende
    // o xetalar dusur bindingResult-e,eyni zamanda biz Validator ile bindingResult-a yene xetalar
    // elave edirik.yeni butun xetalar personla bagli onun icinde saxlanir,saxlayiriq.
    // ve biz xetalari gormek ucun artiq bindingResult-a baxmaliyiq.
    @PostMapping("/register")
    public String registerPerson(@Valid @ModelAttribute("person") Person person
            ,BindingResult bindingResult) {

        // validation-da xeta olarsa o xetalr bindingResult-da saxlanacaq.
        personValidator.validate(person,bindingResult);

        // xeta olarsa yeniden register page-ine yoneltsin
        // orda xeta mesajin gore bilerik.
        // xeta mesajin gormek ucun thymeleaf-de mutleq fields.hasErrors('errorName') yazilmalidir.
        // errorName - bizim error keyleridir.adeten Person-in fieldlari ile eyni adli olurlar.
        if (bindingResult.hasErrors())
            return "register";


        registrationPersonService.register(person);

        // bindingResult-i f
        return "redirect:/auth/login-page";
    }

}
