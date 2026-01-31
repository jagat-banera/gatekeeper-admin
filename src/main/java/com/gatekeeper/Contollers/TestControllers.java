package com.gatekeeper.Contollers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestControllers {

    @GetMapping("/send-mailer")
    public String sendMail(){
        return "Netty Working Sucesfully";
    }

}
