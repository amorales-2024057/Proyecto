package com.andersonmorales.kinalapp.controller;

import com.andersonmorales.kinalapp.entity.Usuario;
import com.andersonmorales.kinalapp.service.IUsuarioService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {

    private final IUsuarioService usuarioService;

    public WebController(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

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
            model.addAttribute("rol", rol);
            model.addAttribute("esAdmin", rol.equals("ADMIN"));

            usuarioService.buscarPorUsername(username)
                    .ifPresentOrElse(
                            u -> {
                                model.addAttribute("nombreCompleto", u.getNombreCompleto());
                                model.addAttribute("codigoUsuario", u.getCodigoUsuario());
                            },
                            () -> {
                                model.addAttribute("nombreCompleto", username);
                                model.addAttribute("codigoUsuario", 0);
                            }
                    );
        }
        return "dashboard";
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

    @GetMapping("/registro")
    public String registroForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }

    @PostMapping("/registro")
    public String registroProcesar(@ModelAttribute Usuario usuario, Model model) {
        boolean registrado = usuarioService.registrar(usuario);
        if (!registrado) {
            model.addAttribute("error", "El username o email ya están en uso.");
            model.addAttribute("usuario", usuario);
            return "registro";
        }
        return "redirect:/login?registrado=true";
    }
}