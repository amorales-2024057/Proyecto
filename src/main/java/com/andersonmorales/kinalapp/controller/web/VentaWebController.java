package com.andersonmorales.kinalapp.controller.web;

import com.andersonmorales.kinalapp.entity.Ventas;
import com.andersonmorales.kinalapp.service.IClienteService;
import com.andersonmorales.kinalapp.service.IUsuarioService;
import com.andersonmorales.kinalapp.service.IVentasService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/web/ventas")
public class VentaWebController {

    private final IVentasService ventasService;
    private final IClienteService clienteService;
    private final IUsuarioService usuarioService;

    public VentaWebController(IVentasService ventasService,
                              IClienteService clienteService,
                              IUsuarioService usuarioService) {
        this.ventasService = ventasService;
        this.clienteService = clienteService;
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String listarVentas(Model model) {
        model.addAttribute("ventas", ventasService.listarTodos());
        model.addAttribute("titulo", "Lista de Ventas");
        return "ventas/lista";
    }

    @GetMapping("/nueva")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("venta", new Ventas());
        model.addAttribute("clientes", clienteService.listarTodos());
        model.addAttribute("usuarios", usuarioService.listarTodos());
        model.addAttribute("titulo", "Nueva Venta");
        model.addAttribute("accion", "guardar");
        return "ventas/formulario";
    }

    @PostMapping("/guardar")
    public String guardarVenta(@ModelAttribute Ventas venta) {
        ventasService.guardar(venta);
        return "redirect:/web/ventas";
    }

    @GetMapping("/editar/{codigo}")
    public String mostrarFormularioEditar(@PathVariable long codigo, Model model) {
        Ventas venta = ventasService.buscarporCodigo(codigo)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
        model.addAttribute("venta", venta);
        model.addAttribute("clientes", clienteService.listarTodos());
        model.addAttribute("usuarios", usuarioService.listarTodos());
        model.addAttribute("titulo", "Editar Venta");
        model.addAttribute("accion", "actualizar");
        return "ventas/formulario";
    }

    @PostMapping("/actualizar/{codigo}")
    public String actualizarVenta(@PathVariable long codigo, @ModelAttribute Ventas venta) {
        ventasService.actualizar(codigo, venta);
        return "redirect:/web/ventas";
    }
}