package com.andersonmorales.kinalapp.service;

import com.andersonmorales.kinalapp.entity.Cliente;
import com.andersonmorales.kinalapp.entity.DetalleVenta;
import com.andersonmorales.kinalapp.entity.Ventas;
import com.andersonmorales.kinalapp.repository.ClienteRepository;
import com.andersonmorales.kinalapp.repository.DetalleVentasRepository;
import com.andersonmorales.kinalapp.repository.VentasRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClienteService implements IClienteService {

    private final ClienteRepository clienteRepository;
    private final VentasRepository ventasRepository;
    private final DetalleVentasRepository detalleVentasRepository;

    public ClienteService(ClienteRepository clienteRepository,
                          VentasRepository ventasRepository,
                          DetalleVentasRepository detalleVentasRepository) {
        this.clienteRepository = clienteRepository;
        this.ventasRepository = ventasRepository;
        this.detalleVentasRepository = detalleVentasRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente guardar(Cliente cliente) {
        validarCliente(cliente);
        if (cliente.getEstado() == 0) {
            cliente.setEstado(1);
        }
        return clienteRepository.save(cliente);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cliente> buscarPorDPI(String dpi) {
        return clienteRepository.findById(dpi);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cliente> buscarPorEstado(int estado) {
        return clienteRepository.findAll()
                .stream()
                .filter(c -> c.getEstado() == estado)
                .findAny();
    }

    @Override
    public Cliente actualizar(String dpi, Cliente cliente) {
        if (!clienteRepository.existsById(dpi)) {
            throw new RuntimeException("Cliente no encontrado con DPI " + dpi);
        }
        cliente.setDPICliente(dpi);
        validarCliente(cliente);
        return clienteRepository.save(cliente);
    }

    @Override
    public void eliminar(String dpi) {
        if (!clienteRepository.existsById(dpi)) {
            throw new RuntimeException("Cliente no encontrado con DPI " + dpi);
        }
        List<Ventas> ventas = ventasRepository.findByClienteDPICliente(dpi);
        for (Ventas venta : ventas) {
            List<DetalleVenta> detalles = detalleVentasRepository.findByVentasCodigoVenta(venta.getCodigoVenta());
            if (!detalles.isEmpty()) {
                detalleVentasRepository.deleteAll(detalles);
                detalleVentasRepository.flush();
            }
        }
        if (!ventas.isEmpty()) {
            ventasRepository.deleteAll(ventas);
            ventasRepository.flush();
        }
        clienteRepository.deleteById(dpi);
    }

    @Override
    public boolean existePorDPI(String dpi) {
        return clienteRepository.existsById(dpi);
    }

    private void validarCliente(Cliente cliente) {
        if (cliente.getDPICliente() == null || cliente.getDPICliente().trim().isEmpty()) {
            throw new IllegalArgumentException("El DPI es un dato obligatorio");
        }
        if (cliente.getNombreCliente() == null || cliente.getNombreCliente().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre es un dato obligatorio");
        }
        if (cliente.getApellidoCliente() == null || cliente.getApellidoCliente().trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido es un dato obligatorio");
        }
    }
}