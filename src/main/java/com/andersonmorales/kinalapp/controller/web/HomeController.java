package com.andersonmorales.kinalapp.controller.web;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        // Verificar si hay sesión
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        model.addAttribute("usuario", session.getAttribute("usuario"));
        return "dashboard";
    }

    @GetMapping("/factura/{codigoVenta}")
    public String verFactura(HttpSession session, Model model) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }
        return "factura";
    }
}