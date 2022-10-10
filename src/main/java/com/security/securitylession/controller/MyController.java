package com.security.securitylession.controller;

import com.security.securitylession.entity.Person;
import com.security.securitylession.security.PersonDetails;
import org.hibernate.Session;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class MyController {
    @GetMapping("/home")
    public String getHome() {

        return "home";
    }






    @GetMapping("/show-person")
    public String getInfoPerson() {

        // ariq PersonDetails ala bilerik
        // PersonDetails SecurityContextHolder-da saxlanir.
        //SecurityContextHolder bizim authenticate olmus userlerimizi saxlayir.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();

        System.out.println(personDetails.getPerson());


        return "home";
    }

}
