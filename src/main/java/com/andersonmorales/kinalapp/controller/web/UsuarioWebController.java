package com.andersonmorales.kinalapp.controller.web;

import com.andersonmorales.kinalapp.entity.Usuario;
import com.andersonmorales.kinalapp.service.IUsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/web/usuarios")
public class UsuarioWebController {

    private final IUsuarioService usuarioService;

    public UsuarioWebController(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.listarTodos());
        model.addAttribute("titulo", "Lista de Usuarios");
        return "usuarios/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("titulo", "Nuevo Usuario");
        model.addAttribute("accion", "guardar");
        return "usuarios/formulario";
    }

    @PostMapping("/guardar")
    public String guardarUsuario(@ModelAttribute Usuario usuario) {
        usuarioService.guardar(usuario);
        return "redirect:/web/usuarios";
    }

    @GetMapping("/editar/{codigo}")
    public String mostrarFormularioEditar(@PathVariable long codigo, Model model) {
        Usuario usuario = usuarioService.buscarPorCodigo(codigo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        model.addAttribute("usuario", usuario);
        model.addAttribute("titulo", "Editar Usuario");
        model.addAttribute("accion", "actualizar");
        return "usuarios/formulario";
    }

    @PostMapping("/actualizar/{codigo}")
    public String actualizarUsuario(@PathVariable long codigo, @ModelAttribute Usuario usuario) {
        usuarioService.actualizar(codigo, usuario);
        return "redirect:/web/usuarios";
    }

    @GetMapping("/eliminar/{codigo}")
    public String eliminarUsuario(@PathVariable long codigo) {
        usuarioService.eliminar(codigo);
        return "redirect:/web/usuarios";
    }
}