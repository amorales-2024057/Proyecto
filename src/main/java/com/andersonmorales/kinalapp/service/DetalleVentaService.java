package com.andersonmorales.kinalapp.service;

import com.andersonmorales.kinalapp.entity.DetalleVenta;
import com.andersonmorales.kinalapp.repository.DetalleVentasRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DetalleVentaService implements IDetalleVentasService {

    private final DetalleVentasRepository detalleVentaRepository;

    public DetalleVentaService(DetalleVentasRepository detalleVentaRepository) {
        this.detalleVentaRepository = detalleVentaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<DetalleVenta> listarTodos() {
        return detalleVentaRepository.findAll();
    }

    @Override
    public DetalleVenta guardar(DetalleVenta detalleVenta) {
        validarDetalleVenta(detalleVenta);
        return detalleVentaRepository.save(detalleVenta);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DetalleVenta> buscarPorCodigo(long codigo) {
        return detalleVentaRepository.findById(codigo);
    }

    @Override
    public DetalleVenta actualizar(long codigo, DetalleVenta detalleVenta) {
        if (!detalleVentaRepository.existsById(codigo)) {
            throw new RuntimeException("Detalle de venta no encontrado con código " + codigo);
        }
        detalleVenta.setCodigoDetalleVenta(codigo);
        validarDetalleVenta(detalleVenta);
        return detalleVentaRepository.save(detalleVenta);
    }

    @Override
    public boolean existePorCodigo(long codigo) {
        return detalleVentaRepository.existsById(codigo);
    }

    private void validarDetalleVenta(DetalleVenta detalleVenta) {
        if (detalleVenta.getCantidad() <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
        }
        if (detalleVenta.getPrecioUnitario() <= 0) {
            throw new IllegalArgumentException("El precio unitario debe ser mayor a 0");
        }
        if (detalleVenta.getProducto() == null) {
            throw new IllegalArgumentException("El producto es obligatorio");
        }
        if (detalleVenta.getVentas() == null) {
            throw new IllegalArgumentException("La venta es obligatoria");
        }
        detalleVenta.setSubtotal(detalleVenta.getCantidad() * detalleVenta.getPrecioUnitario());
    }
}