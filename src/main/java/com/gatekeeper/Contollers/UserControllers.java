package com.gatekeeper.Contollers;

import com.gatekeeper.DTO.AdminSignupRequest;
import com.gatekeeper.Service.AdminSignupService;
import com.gatekeeper.Service.Exceptions.AdminAlreadyExistsException;
import com.gatekeeper.Service.Exceptions.PasswordMismtachException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
public class UserControllers {

    private final AdminSignupService adminSignupService ;

    public UserControllers(AdminSignupService adminSignupService) {
        this.adminSignupService = adminSignupService;
    }


     /* User Controllers

        The Below Controllers are for User Login and Signup
        The user Authorization logic for /login has been done in Spring Security

     */

    @GetMapping("/login")
    public String login(Model model){
        return "login";
    }

    @GetMapping("/admin-signup")
    public String adminSignup(Model model){

        model.addAttribute("adminSignupRequest" , new AdminSignupRequest());

        return "SignupAdmin";
    }

    @PostMapping("/admin-signup")
    public String adminSignupPost(@Valid @ModelAttribute("adminSignupRequest")AdminSignupRequest request,
                                 BindingResult bindingResult ,
                                 Model model){

        if(bindingResult.hasErrors()){
            return "SignupAdmin";
        }

        // Call the service

        try {
            adminSignupService.createAdmin(request);
            return "redirect:/login";
        }
        catch (PasswordMismtachException | AdminAlreadyExistsException e){
            model.addAttribute("globalError" , e.getMessage());
            return "SignupAdmin";
        }

    }


}
