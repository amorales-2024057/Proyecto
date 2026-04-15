package com.andersonmorales.kinalapp.controller.web;

import com.andersonmorales.kinalapp.entity.Productos;
import com.andersonmorales.kinalapp.service.IProductosService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/web/productos")
public class ProductoWebController {

    private final IProductosService productosService;

    public ProductoWebController(IProductosService productosService) {
        this.productosService = productosService;
    }

    @GetMapping
    public String listarProductos(Model model) {
        model.addAttribute("productos", productosService.listarTodos());
        model.addAttribute("titulo", "Lista de Productos");
        return "productos/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("producto", new Productos());
        model.addAttribute("titulo", "Nuevo Producto");
        model.addAttribute("accion", "guardar");
        return "productos/formulario";
    }

    @PostMapping("/guardar")
    public String guardarProducto(@ModelAttribute Productos producto) {
        productosService.guardar(producto);
        return "redirect:/web/productos";
    }

    @GetMapping("/editar/{codigo}")
    public String mostrarFormularioEditar(@PathVariable long codigo, Model model) {
        Productos producto = productosService.buscarPorCodigo(codigo)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        model.addAttribute("producto", producto);
        model.addAttribute("titulo", "Editar Producto");
        model.addAttribute("accion", "actualizar");
        return "productos/formulario";
    }

    @PostMapping("/actualizar/{codigo}")
    public String actualizarProducto(@PathVariable long codigo, @ModelAttribute Productos producto) {
        productosService.actualizar(codigo, producto);
        return "redirect:/web/productos";
    }

    @GetMapping("/eliminar/{codigo}")
    public String eliminarProducto(@PathVariable long codigo) {
        productosService.eliminar(codigo);
        return "redirect:/web/productos";
    }
}