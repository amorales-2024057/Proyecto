package com.andersonmorales.kinalapp.service;

import com.andersonmorales.kinalapp.entity.Cliente;

import java.util.List;
import java.util.Optional;

public interface IClienteService {
    //Interfaz: Es un contrato que dice QUE métodos deben tener
    //cualquier servicio de Cliente, No tiene
    //implementación, solo la definición de los métodos

    //Metodo que devuelve una lista de todos los clientes
    List<Cliente> listarTodos();
    //List<Cliente> lo que hace es devolver una lista
    //de objetos de la entidad Clientes

    //Metodo que guarda un Cliente en la BD
    Cliente guardar(Cliente cliente);
    //Parámetros - Recibe un objeto Cliente con los datos a guardar

    //Optional - Contenedor que puede o no tener un valor
    //evitar el error de NullPoniterException
    Optional<Cliente> buscarPorDPI(String dpi);
    Optional<Cliente> buscarPorEstado(int estado);

    //Metodo que actualiza un Cliente
    Cliente actualizar(String dpi, Cliente cliente);
    //Parametros - dpi: DPI del Cliente a actualizar
    //Cliente cliente: Objeto con los datos nuevos
    //y este retorna un objeto de tipo Cliente ya actualizado

    // Metodo de tipo void para eliminar a un cliente
    //void: no retorna ningún dato
    //Elimina un cliente por su DPI
    void eliminar(String dpi);

    //boolean - Retorna true si existe, false si no existe
    boolean existePorDPI(String dpi);


}
