package com.andersonmorales.kinalapp.service;

import com.andersonmorales.kinalapp.entity.Ventas;
import com.andersonmorales.kinalapp.repository.VentasRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VentasService implements IVentasService {

    private final VentasRepository ventasRepository;

    public VentasService(VentasRepository ventaRepository) {
        this.ventasRepository = ventaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Ventas> listarTodos() {
        return ventasRepository.findAll();
    }

    @Override
    public Ventas guardar(Ventas ventas) {
        validarVenta(ventas);
        if (ventas.getEstado() == 0) {
            ventas.setEstado(1);
        }
        return ventasRepository.save(ventas);
    }

    @Override
    public Optional<Ventas> buscarporCodigo(long codigoVentas) {
        return ventasRepository.findById(codigoVentas);
    }

    @Override
    public Optional<Ventas> buscarporEstado(int estado) {
        return ventasRepository.findAll()
                .stream()
                .filter(venta -> venta.getEstado() == estado)
                .findAny();
    }

    @Override
    public Ventas actualizar(long codigoVentas, Ventas ventas) {
        if (!ventasRepository.existsById(codigoVentas)) {
            throw new RuntimeException("Venta no encontrada con código " + codigoVentas);
        }
        ventas.setCodigoVenta(codigoVentas);
        validarVenta(ventas);
        return ventasRepository.save(ventas);
    }

    @Override
    public boolean existeporCodigo(long codigoVentas) {
        return ventasRepository.existsById(codigoVentas);
    }

    private void validarVenta(Ventas venta) {
        if (venta.getFechaVenta() == null) {
            throw new IllegalArgumentException("La fecha de venta es obligatoria");
        }
        if (venta.getTotal() < 0) {
            throw new IllegalArgumentException("El total no puede ser negativo");
        }
        if (venta.getCliente() == null) {
            throw new IllegalArgumentException("El cliente es obligatorio");
        }
        if (venta.getUsuario() == null) {
            throw new IllegalArgumentException("El usuario es obligatorio");
        }
    }
}