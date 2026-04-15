package com.andersonmorales.kinalapp.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Ventas")
public class Ventas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_venta")
    private long codigoVenta;
    @Column(name = "fecha_venta", nullable = false)
    private LocalDate fechaVenta;
    @Column(name = "total", nullable = false)
    private double total;
    @Column(name = "estado", nullable = false)
    private int estado;
    @ManyToOne
    @JoinColumn(name = "Clientes_dpi_cliente", nullable = false)
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "Usuarios_codigo_usuario", nullable = false)
    private Usuario usuario;

    public Ventas(){}

    public Ventas(long codigoVenta, LocalDate fechaVenta, double total, int estado, Cliente cliente, Usuario usuario){
        this.codigoVenta = codigoVenta;
        this.fechaVenta = fechaVenta;
        this.total = total;
        this.estado = estado;
        this.cliente = cliente;
        this.usuario = usuario;
    }

    public long getCodigoVenta() {
        return codigoVenta;
    }

    public void setCodigoVenta(long codigoVenta) {
        this.codigoVenta = codigoVenta;
    }

    public LocalDate getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(LocalDate fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}