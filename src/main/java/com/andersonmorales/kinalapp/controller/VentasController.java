package com.andersonmorales.kinalapp.controller;

import com.andersonmorales.kinalapp.entity.Ventas;
import com.andersonmorales.kinalapp.service.IVentasService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ventas")

public class VentasController {

    private final IVentasService ventasService;

    public VentasController(IVentasService ventasService) {
        this.ventasService = ventasService;
    }

    @GetMapping
    public ResponseEntity<List<Ventas>> listar() {
        List<Ventas> ventas = ventasService.listarTodos();
        return ResponseEntity.ok(ventas);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Ventas> buscarPorCodigo(@PathVariable long codigo) {
        return ventasService.buscarporCodigo(codigo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<Ventas> buscarPorEstado(@PathVariable int estado) {
        return ventasService.buscarporEstado(estado)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Ventas venta) {
        try {
            Ventas nuevaVenta = ventasService.guardar(venta);
            return new ResponseEntity<>(nuevaVenta, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<?> actualizar(@PathVariable long codigo, @RequestBody Ventas ventas) {
        try {
            if (!ventasService.existeporCodigo(codigo)) {
                return ResponseEntity.notFound().build();
            }
            Ventas ventaActualizada = ventasService.actualizar(codigo, ventas);
            return ResponseEntity.ok(ventaActualizada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}