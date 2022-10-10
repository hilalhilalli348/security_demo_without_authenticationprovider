package com.security.securitylession.controller;

import com.security.securitylession.security.PersonDetails;
import org.hibernate.Session;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class MyController {
    @GetMapping("/home")
    public String getHome() {
        // session RequestContextHolder-da tutulur.
//        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
//        HttpSession session = attr.getRequest().getSession(true); // true == allow create
//
//        Integer count = (Integer) session.getAttribute("count");
//        if (count==null){
//            count=1;
//            session.setAttribute("count",count);
//        }
//
//        System.out.println(count);
//        session.setAttribute("count",++count);

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
