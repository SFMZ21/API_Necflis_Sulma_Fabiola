package com.example.demo.controllers;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entities.Cliente;
import com.example.demo.repositories.ClienteRepository;

@RestController
@RequestMapping(value ="/clientes")
public class ClienteController {

    @Autowired
    ClienteRepository clienteRepository;
//metodo crear clientes
    @PostMapping
    @ResponseStatus(code =HttpStatus.CREATED)
    public Cliente crearClientes(@RequestBody Cliente cliente,@DateTimeFormat(pattern="yyyy.MM.ddd")Date date) {
        Cliente nuevoCliente= clienteRepository.save(cliente);

        return nuevoCliente;
    }

//metodo actualizar informacion del cliente
    @PutMapping(value="/{id}")
    @ResponseStatus(code=HttpStatus.ACCEPTED)
    public Cliente actualizarInfo(@PathVariable(name="id")Long id,@RequestBody Cliente cliente ){
        Optional<Cliente>informacion= clienteRepository.findById(id);
        if(informacion.isPresent()){
            Cliente clienteActualizado= informacion.get();
            clienteActualizado.setId(id);
            clienteActualizado.setNombre(cliente.getNombre());
            clienteActualizado.setEdad(cliente.getEdad());
            clienteActualizado.setGenero(cliente.getGenero());
            clienteActualizado.setPais(cliente.getPais());
            clienteActualizado.setFechaCreacion(cliente.getFechaCreacion());

            Cliente clienteGuardado = clienteRepository.save(clienteActualizado);
            return clienteGuardado;
        }
        return null;
    }

    //borrar cliente
    @DeleteMapping(value="/{id}")
    @ResponseStatus(code=HttpStatus.ACCEPTED)
    public void borrarCliente(@PathVariable(name="id")Long id){
        clienteRepository.deleteById(id);
    }

    //listar clientes
    @GetMapping
    @ResponseStatus(code=HttpStatus.OK)
    public Collection<Cliente>listarClientes(){
        Iterable<Cliente>clientes=clienteRepository.findAll();

        return (Collection<Cliente>) clientes;
    }

    @GetMapping(value="/{id}")
    @ResponseStatus(code=HttpStatus.OK)
    public Cliente buscarCliente(@PathVariable(name="id")Long id){
        Optional<Cliente>buscar= clienteRepository.findById(id);
        Cliente clienteBuscado =null;
        if(buscar.isPresent()){
            clienteBuscado= buscar.get();
        }
        return clienteBuscado;
    }




}