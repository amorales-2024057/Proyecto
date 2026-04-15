package com.andersonmorales.kinalapp.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;

@Entity
@Table(name = "Usuarios")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_usuario")
    private Long codigoUsuario;  // Cambiado de long a Long

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "nombre_completo", nullable = false)
    private String nombreCompleto;

    @Column(name = "rol", nullable = false)
    private String rol;

    @Column(name = "estado", nullable = false)
    private Integer estado;  // Cambiado de int a Integer

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    @Column(name = "ultimo_login")
    private LocalDateTime ultimoLogin;

    public Usuario() {}

    public Usuario(Long codigoUsuario, String username, String password, String email,
                   String nombreCompleto, String rol, Integer estado,
                   LocalDateTime fechaRegistro, LocalDateTime ultimoLogin) {
        this.codigoUsuario = codigoUsuario;
        this.username = username;
        this.password = password;
        this.email = email;
        this.nombreCompleto = nombreCompleto;
        this.rol = rol;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
        this.ultimoLogin = ultimoLogin;
    }

    // Getters y Setters
    public Long getCodigoUsuario() { return codigoUsuario; }
    public void setCodigoUsuario(Long codigoUsuario) { this.codigoUsuario = codigoUsuario; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public Integer getEstado() { return estado; }
    public void setEstado(Integer estado) { this.estado = estado; }

    public LocalDateTime getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDateTime fechaRegistro) { this.fechaRegistro = fechaRegistro; }

    public LocalDateTime getUltimoLogin() { return ultimoLogin; }
    public void setUltimoLogin(LocalDateTime ultimoLogin) { this.ultimoLogin = ultimoLogin; }
}