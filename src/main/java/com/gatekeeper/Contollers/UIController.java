package com.gatekeeper.Contollers;

import com.gatekeeper.DTO.RouteDTO;
import com.gatekeeper.Service.RouteService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ui")
public class UIController {

    private final RouteService routeService ;


    public UIController(RouteService routeService) {
        this.routeService = routeService;

    }


    // List ALL APIs

    @GetMapping("/list-api")
    public String listRoutes(Model model){

        model.addAttribute("routes" , routeService.showAllRoutes());

        return "ListAPIs";
    }


    @GetMapping("/register-api")
    public String showRegisterForm(Model model){

        model.addAttribute("route" , new RouteDTO());

        return "RegisterAPIs";

    }


    @PostMapping("/register-api")
    public String register(@Valid @ModelAttribute("route") RouteDTO routeDTO ,
                           BindingResult bindingResult , Model model){

        if(bindingResult.hasErrors()){
            // From has errors
            return "RegisterAPIs";
        }

        // Register the Route
        routeService.registerRoute(routeDTO);

        return "redirect:/ui/list-api";


    }

    // ------------ MANAGE ROUTES CONTROLLER ------------------- //

    @GetMapping("/manage-api")
    public String ManageApi(Model model){
        model.addAttribute("routes" , routeService.showAllRoutes());

        return "EnableAPI";
    }


    @PostMapping("/manage-api/delete/{id}")
    public String deleteRoute(@PathVariable Integer id) {

        return "redirect:/ui/list-api";
    }

    @PostMapping("/manage-api/activate/{id}")
    public String activateRoute(@PathVariable Integer id) {

        routeService.activateAPI(id);

        return "redirect:/ui/list-api";

    }

//    @PostMapping("/manage-api/deactivate/{id}")
//    public String deactivateRoute(@PathVariable Integer id) {
//
//        dynamicRoutes.DeleteRoute(id);
//
//        return "redirect:/ui/list-api" ;
//
//    }

}
