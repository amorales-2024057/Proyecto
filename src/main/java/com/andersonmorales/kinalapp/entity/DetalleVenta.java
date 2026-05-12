package com.andersonmorales.kinalapp.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "DetalleVenta")
@JsonIgnoreProperties(ignoreUnknown = true)
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_detalle_venta")
    private Long codigoDetalleVenta;  // Cambiado de long a Long

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;  // Cambiado de int a Integer

    @Column(name = "precio_unitario", nullable = false)
    private Double precioUnitario;  // Cambiado de double a Double

    @Column(name = "subtotal", nullable = false)
    private Double subtotal;  // Cambiado de double a Double

    @ManyToOne
    @JoinColumn(name = "Productos_codigo_producto", nullable = false)
    private Productos productos;

    @ManyToOne
    @JoinColumn(name = "Ventas_codigo_venta", nullable = false)
    private Ventas ventas;

    public DetalleVenta() {}

    public DetalleVenta(Long codigoDetalleVenta, Integer cantidad, Double precioUnitario, Double subtotal, Productos productos, Ventas ventas) {
        this.codigoDetalleVenta = codigoDetalleVenta;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
        this.productos = productos;
        this.ventas = ventas;
    }

    public Long getCodigoDetalleVenta() {
        return codigoDetalleVenta;
    }

    public void setCodigoDetalleVenta(Long codigoDetalleVenta) {
        this.codigoDetalleVenta = codigoDetalleVenta;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Productos getProducto() {
        return productos;
    }

    public void setProducto(Productos productos) {
        this.productos = productos;
    }

    public Ventas getVentas() {
        return ventas;
    }

    public void setVentas(Ventas ventas) {
        this.ventas = ventas;
    }
}