package com.andersonmorales.kinalapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "DetalleVenta")
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_detalle_venta")
    private long codigoDetalleVenta;
    @Column(name = "cantidad", nullable = false)
    private int cantidad;
    @Column(name = "precio_unitario", nullable = false)
    private double precioUnitario;
    @Column(name = "subtotal", nullable = false)
    private double subtotal;
    @ManyToOne
    @JoinColumn(name = "Productos_codigo_producto", nullable = false)
    private Productos productos;
    @ManyToOne
    @JoinColumn(name = "Ventas_codigo_venta", nullable = false)
    private Ventas ventas;

    public DetalleVenta(){}

    public DetalleVenta(long codigoDetalleVenta, int cantidad, double precioUnitario, double subtotal, Productos productos, Ventas ventas){
        this.codigoDetalleVenta = codigoDetalleVenta;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
        this.productos = productos;
        this.ventas = ventas;
    }

    public long getCodigoDetalleVenta() {
        return codigoDetalleVenta;
    }

    public void setCodigoDetalleVenta(long codigoDetalleVenta) {
        this.codigoDetalleVenta = codigoDetalleVenta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
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