package com.andersonmorales.kinalapp.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {

    @GetMapping("/")
    public String home(Authentication authentication, Model model) {
        if (authentication != null) {
            String username = authentication.getName();
            String rol = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .findFirst()
                    .orElse("ROLE_USER")
                    .replace("ROLE_", "");

            model.addAttribute("username", username);
            model.addAttribute("nombreCompleto", username);
            model.addAttribute("rol", rol);
            model.addAttribute("codigoUsuario", 1);
        }
        return "dashboard";
    }

    @GetMapping("/admin")
    public String admin(Authentication authentication, Model model) {
        if (authentication != null) {
            String username = authentication.getName();
            String rol = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .findFirst()
                    .orElse("ROLE_ADMIN")
                    .replace("ROLE_", "");

            model.addAttribute("username", username);
            model.addAttribute("rol", rol);
        }
        return "admin";
    }

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        Model model) {
        if (error != null) {
            model.addAttribute("error", "Usuario o contraseña incorrectos.");
        }
        if (logout != null) {
            model.addAttribute("logout", "Sesión cerrada correctamente.");
        }
        return "login";
    }
}