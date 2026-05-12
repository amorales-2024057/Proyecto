package com.andersonmorales.kinalapp.controller;

import com.andersonmorales.kinalapp.entity.Productos;
import com.andersonmorales.kinalapp.service.IProductosService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")

public class ProductosController {

    private final IProductosService productosService;

    public ProductosController(IProductosService productoService) {
        this.productosService = productoService;
    }

    @GetMapping
    public ResponseEntity<List<Productos>> listar() {

        List<Productos> productos = productosService.listarTodos();

        return ResponseEntity.ok(productos);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Productos> buscarPorCodigo(@PathVariable long codigo) {

        return productosService.buscarPorCodigo(codigo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<Productos> buscarPorEstado(@PathVariable int estado) {

        return productosService.buscarPorEstado(estado)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Productos producto) {

        try {

            Productos nuevoProducto = productosService.guardar(producto);

            return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED);

        } catch (IllegalArgumentException e) {

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> eliminar(@PathVariable long codigo) {

        try {

            if (!productosService.existePorCodigo(codigo)) {
                return ResponseEntity.notFound().build();
            }

            productosService.eliminar(codigo);

            return ResponseEntity.noContent().build();

        } catch (RuntimeException e) {

            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<?> actualizar(@PathVariable long codigo, @RequestBody Productos producto) {

        try {

            if (!productosService.existePorCodigo(codigo)) {
                return ResponseEntity.notFound().build();
            }

            Productos productoActualizado = productosService.actualizar(codigo, producto);

            return ResponseEntity.ok(productoActualizado);

        } catch (IllegalArgumentException e) {

            return ResponseEntity.badRequest().body(e.getMessage());

        } catch (RuntimeException e) {

            return ResponseEntity.notFound().build();
        }
    }
}