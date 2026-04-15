package com.andersonmorales.kinalapp.controller.web;

import com.andersonmorales.kinalapp.entity.Cliente;
import com.andersonmorales.kinalapp.service.IClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/web/clientes")
public class ClienteWebController {

    private final IClienteService clienteService;

    public ClienteWebController(IClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public String listarClientes(Model model) {
        model.addAttribute("clientes", clienteService.listarTodos());
        model.addAttribute("titulo", "Lista de Clientes");
        return "clientes/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("cliente", new Cliente());
        model.addAttribute("titulo", "Nuevo Cliente");
        model.addAttribute("accion", "guardar");
        return "clientes/formulario";
    }

    @PostMapping("/guardar")
    public String guardarCliente(@ModelAttribute Cliente cliente) {
        clienteService.guardar(cliente);
        return "redirect:/web/clientes";
    }

    @GetMapping("/editar/{dpi}")
    public String mostrarFormularioEditar(@PathVariable String dpi, Model model) {
        Cliente cliente = clienteService.buscarPorDPI(dpi)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        model.addAttribute("cliente", cliente);
        model.addAttribute("titulo", "Editar Cliente");
        model.addAttribute("accion", "actualizar");
        return "clientes/formulario";
    }

    @PostMapping("/actualizar/{dpi}")
    public String actualizarCliente(@PathVariable String dpi, @ModelAttribute Cliente cliente) {
        clienteService.actualizar(dpi, cliente);
        return "redirect:/web/clientes";
    }

    @GetMapping("/eliminar/{dpi}")
    public String eliminarCliente(@PathVariable String dpi) {
        clienteService.eliminar(dpi);
        return "redirect:/web/clientes";
    }
}