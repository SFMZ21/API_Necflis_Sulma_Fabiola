package com.example.demo.repositories;

import com.example.demo.entities.Cliente;

import org.springframework.data.repository.CrudRepository;

public interface ClienteRepository extends CrudRepository<Cliente,Long>{

}
