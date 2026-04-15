package com.andersonmorales.kinalapp.controller;

import com.andersonmorales.kinalapp.entity.Usuario;
import com.andersonmorales.kinalapp.service.IUsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    private final IUsuarioService usuarioService;

    public AuthController(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/login")
    public String mostrarLogin(Model model, HttpSession session) {
        // Si ya está logueado, redirigir al dashboard
        if (session.getAttribute("usuario") != null) {
            return "redirect:/dashboard";
        }
        model.addAttribute("usuario", new Usuario());
        return "login";
    }

    @PostMapping("/login")
    public String procesarLogin(@RequestParam String username,
                                @RequestParam String password,
                                HttpSession session,
                                Model model) {
        Usuario usuario = usuarioService.login(username, password);
        if (usuario != null) {
            session.setAttribute("usuario", usuario);
            session.setAttribute("username", usuario.getUsername());
            session.setAttribute("rol", usuario.getRol());
            return "redirect:/dashboard";
        } else {
            model.addAttribute("error", "Usuario o contraseña incorrectos");
            return "login";
        }
    }

    @GetMapping("/registro")
    public String mostrarRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }

    @PostMapping("/registro")
    public String procesarRegistro(@ModelAttribute Usuario usuario, Model model) {
        boolean registrado = usuarioService.registrar(usuario);
        if (registrado) {
            return "redirect:/login?registro=exitoso";
        } else {
            model.addAttribute("error", "El username o email ya están registrados");
            return "registro";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}