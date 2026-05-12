package com.andersonmorales.kinalapp.controller;

import com.andersonmorales.kinalapp.entity.DetalleVenta;
import com.andersonmorales.kinalapp.service.IDetalleVentasService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detalleVentas")

public class DetalleVentaController {

    private final IDetalleVentasService detalleVentaService;

    public DetalleVentaController(IDetalleVentasService detalleVentaService) {
        this.detalleVentaService = detalleVentaService;
    }

    @GetMapping
    public ResponseEntity<List<DetalleVenta>> listar() {
        List<DetalleVenta> detalles = detalleVentaService.listarTodos();
        return ResponseEntity.ok(detalles);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<DetalleVenta> buscarPorCodigo(@PathVariable long codigo) {
        return detalleVentaService.buscarPorCodigo(codigo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody DetalleVenta detalleVenta) {
        try {
            DetalleVenta nuevoDetalle = detalleVentaService.guardar(detalleVenta);
            return new ResponseEntity<>(nuevoDetalle, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<?> actualizar(@PathVariable long codigo, @RequestBody DetalleVenta detalleVenta) {
        try {
            if (!detalleVentaService.existePorCodigo(codigo)) {
                return ResponseEntity.notFound().build();
            }
            DetalleVenta detalleActualizado = detalleVentaService.actualizar(codigo, detalleVenta);
            return ResponseEntity.ok(detalleActualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}