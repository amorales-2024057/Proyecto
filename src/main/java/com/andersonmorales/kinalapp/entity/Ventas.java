package com.andersonmorales.kinalapp.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDate;

@Entity
@Table(name = "Ventas")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Ventas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_venta")
    private Long codigoVenta;  // Cambiado de long a Long

    @Column(name = "fecha_venta", nullable = false)
    private LocalDate fechaVenta;

    @Column(name = "total", nullable = false)
    private Double total;  // Cambiado de double a Double

    @Column(name = "estado", nullable = false)
    private Integer estado;  // Cambiado de int a Integer

    @ManyToOne
    @JoinColumn(name = "Clientes_dpi_cliente", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "Usuarios_codigo_usuario", nullable = false)
    private Usuario usuario;

    public Ventas() {}

    public Ventas(Long codigoVenta, LocalDate fechaVenta, Double total, Integer estado, Cliente cliente, Usuario usuario) {
        this.codigoVenta = codigoVenta;
        this.fechaVenta = fechaVenta;
        this.total = total;
        this.estado = estado;
        this.cliente = cliente;
        this.usuario = usuario;
    }

    public Long getCodigoVenta() {
        return codigoVenta;
    }

    public void setCodigoVenta(Long codigoVenta) {
        this.codigoVenta = codigoVenta;
    }

    public LocalDate getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(LocalDate fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
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