package com.gatekeeper.Contollers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserControllers {

     /* User Controllers

        The Below Controllers are for User Login and Signup
        The user Authorization logic for /login has been done in Spring Security

     */

    @GetMapping("/login")
    public String login(Model model){
        return "login";
    }

}
