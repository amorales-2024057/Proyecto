package com.andersonmorales.kinalapp.service;

import com.andersonmorales.kinalapp.entity.Productos;
import com.andersonmorales.kinalapp.repository.ProductosRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductosService implements IProductosService {

    private final ProductosRepository productosRepository;

    public ProductosService(ProductosRepository productoRepository) {
        this.productosRepository = productoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Productos> listarTodos() {

        return productosRepository.findAll();
    }

    @Override
    public Productos guardar(Productos productos) {

        validarProducto(productos);

        if (productos.getEstado() == 0) {
            productos.setEstado(1);
        }

        return productosRepository.save(productos);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Productos> buscarPorCodigo(long codigo) {

        return productosRepository.findById(codigo);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Productos> buscarPorEstado(int estado) {

        return productosRepository.findAll()
                .stream()
                .filter(producto -> producto.getEstado() == estado)
                .findAny();
    }

    @Override
    public Productos actualizar(long codigo, Productos producto) {

        if (!productosRepository.existsById(codigo)) {
            throw new RuntimeException("Producto no encontrado con código " + codigo);
        }

        producto.setCodigoProducto(codigo);

        validarProducto(producto);

        return productosRepository.save(producto);
    }

    @Override
    public void eliminar(long codigo) {

        if (!productosRepository.existsById(codigo)) {
            throw new RuntimeException("Producto no encontrado con código " + codigo);
        }

        productosRepository.deleteById(codigo);
    }

    @Override
    public boolean existePorCodigo(long codigo) {

        return productosRepository.existsById(codigo);
    }

    private void validarProducto(Productos producto) {

        if (producto.getNombreProducto() == null || producto.getNombreProducto().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto es obligatorio");
        }

        if (producto.getPrecio() <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a 0");
        }

        if (producto.getStock() < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }
    }
}