package com.andersonmorales.kinalapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Productos")
public class Productos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_producto")
    private long codigoProducto;
    @Column(name = "nombre_producto", nullable = false)
    private String nombreProducto;
    @Column(name = "precio", nullable = false)
    private double precio;
    @Column(name = "stock", nullable = false)
    private long stock;
    @Column(name = "estado", nullable = false)
    private int estado;

    public Productos(){}

    public Productos(long codigoProducto, String nombreProducto, double precio, long stock, int estado){
        this.codigoProducto = codigoProducto;
        this.nombreProducto = nombreProducto;
        this.precio = precio;
        this.stock = stock;
        this.estado = estado;
    }

    public long getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(long codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public long getStock() {
        return stock;
    }

    public void setStock(long stock) {
        this.stock = stock;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}