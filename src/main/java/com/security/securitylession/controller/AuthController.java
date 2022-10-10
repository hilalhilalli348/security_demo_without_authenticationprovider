package com.security.securitylession.controller;

import com.security.securitylession.entity.Person;
import com.security.securitylession.services.RegistrationPersonService;
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

    @Autowired
    public AuthController(RegistrationPersonService registrationPersonService) {
        this.registrationPersonService = registrationPersonService;
    }

    @GetMapping("/login-page")
    public String login() {

        return "login";
    }

    @GetMapping("/register")
    public String getRegister(@ModelAttribute("person") Person person) {

        return "register";
    }


    @PostMapping("/register")
    public String registerPerson(@ModelAttribute("person") Person person) {

            registrationPersonService.register(person);

        return "redirect:/auth/login-page";
    }

}
