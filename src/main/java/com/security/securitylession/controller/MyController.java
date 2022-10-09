package com.security.securitylession.controller;

import com.security.securitylession.security.PersonDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {
    @GetMapping("/home")
    public String getHome(){
        return "home";
    }

    @GetMapping("/show-person")
    public String getInfoPerson(){

        // ariq PersonDetails ala bilerik
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();

        System.out.println(personDetails.getPerson());


        return "home";
    }

}
