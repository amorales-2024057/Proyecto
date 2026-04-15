package com.andersonmorales.kinalapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "clientes")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Cliente {

    @Id
    @Column(name = "dpi_cliente")
    private String dpiCliente;

    @Column(name = "nombreCliente", nullable = false)
    private String nombreCliente;

    @Column(name = "apellidoCliente", nullable = false)
    private String apellidoCliente;

    @Column(name = "direccion", nullable = false)
    private String direccion;

    @Column(name = "estado", nullable = false)
    private Integer estado;  // Cambiado de int a Integer

    public Cliente() {}

    public Cliente(String dpiCliente, String nombreCliente, String apellidoCliente, String direccion, Integer estado) {
        this.dpiCliente = dpiCliente;
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
        this.direccion = direccion;
        this.estado = estado;
    }

    public String getDPICliente() {
        return dpiCliente;
    }

    public void setDPICliente(String dpiCliente) {
        this.dpiCliente = dpiCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getApellidoCliente() {
        return apellidoCliente;
    }

    public void setApellidoCliente(String apellidoCliente) {
        this.apellidoCliente = apellidoCliente;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }
}