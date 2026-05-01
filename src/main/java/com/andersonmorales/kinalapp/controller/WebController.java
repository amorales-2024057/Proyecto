package com.andersonmorales.kinalapp.controller;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("successMessage", "Conexión establecida exitosamente");

        return "principal";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
