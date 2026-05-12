package com.andersonmorales.kinalapp.service;


import com.andersonmorales.kinalapp.entity.Cliente;
import com.andersonmorales.kinalapp.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//Anotación que registra un Bean como un Bean de Spring
//Que la clase contiene la logica del negocio
@Service
//Por defecto todos los metodos de esta clase serán
//Transaccionales
//Una transacción es que puede o no ocurrir algo
@Transactional
public class ClienteService implements IClienteService{
    /*private
     */
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    /*
     *@Override: Indicar que estamos implementando un metodo de la interfaz
     */

    @Override
    /*
     * readOnly=true: lo que hace es optimizar la cunsulta, no bloquea la BD
     */
    @Transactional(readOnly = true)
    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
        /*
         * Llama al metodo findAll() del repositorio en Spring Data Jpa
         * este metodo hace exactamente el Select * from Clientes
         */
    }

    @Override
    public Cliente guardar(Cliente cliente) {
        /*
         *Metodo de guardar crea un Cliente
         * Acá es donde colocamos la logica del negocio antes de guardar
         * primero validamos el dato
         */
        validarCliente(cliente);
        if(cliente.getEstado() == 0){
            cliente.setEstado(1);
        }
        return clienteRepository.save(cliente);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cliente> buscarPorDPI(String dpi) {
        //Buscar un cliente por DPI
        return clienteRepository.findById(dpi);
        //Optional nos evita el NullPointerException
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cliente> buscarPorEstado(int estado) {
        return clienteRepository.findAll()
                .stream()
                .filter(cliente -> cliente.getEstado() == estado)
                .findAny();
    }

    @Override
    public Cliente actualizar(String dpi, Cliente cliente) {
        //Actualiza un cliente existente
        if(!clienteRepository.existsById(dpi)){
            throw new RuntimeException("Cliente no se encontró con DPI" + dpi);
            //si no existe, se lanza una excepción (error controlado)
        }
        /*
         * 1. Asegurar que el DPI del objeto coincida con el de la URL
         * 2. por seguridad usamos el DPI de la URL y no el que viene en el JSON
         */
        cliente.setDPICliente(dpi);
        validarCliente(cliente);

        return clienteRepository.save(cliente);
    }

    @Override
    public void eliminar(String dpi) {
        //Eliminar un cliente
        if(!clienteRepository.existsById(dpi)){
            throw new RuntimeException("El cliente no se encontró con el DPI" + dpi);
        }
        clienteRepository.deleteById(dpi);
    }

    @Override
    public boolean existePorDPI(String dpi) {
        //Verificar si existe el cliente
        return clienteRepository.existsById(dpi);
        //retorna true o false

    }

    //Metodo privado(solo se pueden utilizar dentro de la clase)
    private void validarCliente(Cliente cliente){
        /*
         * Validaciones del negocio: Este metodo se hará privado porque
         * es algo interno del servicio
         */
        if (cliente.getDPICliente() == null || cliente.getDPICliente().trim().isEmpty()){
            //Si el DPI es null o esta vacío despues de quietar espacios
            //Lanza una excepción con un mensaje
            throw new IllegalArgumentException("El DPI es un dato obligatorio");
        }

        if(cliente.getNombreCliente() == null || cliente.getNombreCliente().trim().isEmpty()){
            throw new IllegalArgumentException("El nombre es un dato obligatorio");
        }

        if (cliente.getApellidoCliente() == null || cliente.getApellidoCliente().trim().isEmpty()){
            throw new IllegalArgumentException("El apellido es un dato obligatorio");
        }
    }
}
