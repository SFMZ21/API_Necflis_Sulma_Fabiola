package com.example.demo.controllers;

import com.example.demo.entities.Pago;
import com.example.demo.repositories.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping(value="/pagos")
public class PagoController {
    @Autowired
    PagoRepository pagoRepository;

    @PostMapping
    @ResponseStatus(code= HttpStatus.CREATED)
    public Pago realizarPago(@RequestBody Pago pago, @DateTimeFormat(pattern = "yyy.MM.ddd") Date date){
        Pago nuevoPago=pagoRepository.save(pago);

        return nuevoPago;
    }

    @PutMapping(value="/{id}")
    @ResponseStatus(code=HttpStatus.ACCEPTED)
    public Pago actualizarPago(@PathVariable(name="id")Long id, @RequestBody Pago pago ){
        Optional<Pago> informacion= pagoRepository.findById(id);
        if(informacion.isPresent()){
            Pago pagoActualizado= informacion.get();
            pagoActualizado.setId(id);
            pagoActualizado.setFecha(pago.getFecha());
            pagoActualizado.setMonto(pago.getMonto());
            pagoActualizado.setNumeroTarjeta(pago.getNumeroTarjeta());
            pagoActualizado.setEstado(pago.getEstado());

            Pago pagoGuardado = pagoRepository.save(pagoActualizado);
            return pagoGuardado;
        }
        return null;
    }


    @DeleteMapping(value="/{id}")
    @ResponseStatus(code=HttpStatus.ACCEPTED)
    public void borrarPago(@PathVariable(name="id")Long id){
        pagoRepository.deleteById(id);
    }

    @GetMapping
    @ResponseStatus(code=HttpStatus.OK)
    public Collection<Pago> listarPagos(){
        Iterable<Pago> pagos=pagoRepository.findAll();

        return (Collection<Pago>)pagos;
    }

    @GetMapping(value="/{id}")
    @ResponseStatus(code=HttpStatus.OK)
    public Pago buscarPago(@PathVariable(name="id")Long id){
        Optional<Pago>buscar= pagoRepository.findById(id);
        Pago pagoBuscado =null;
        if(buscar.isPresent()){
            pagoBuscado= buscar.get();
        }
        return pagoBuscado;
    }

}
