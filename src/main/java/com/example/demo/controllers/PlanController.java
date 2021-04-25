package com.example.demo.controllers;


import com.example.demo.entities.Cliente;
import com.example.demo.entities.Plan;
import com.example.demo.repositories.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping(value = "/planes")
public class PlanController {

    @Autowired
    PlanRepository planRepository;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Plan crearPlan(@RequestBody Plan plan, @DateTimeFormat(pattern="yyyy.MM.ddd") Date date){
        Plan nuevoPlan=planRepository.save(plan);

        return nuevoPlan;
    }

    @PutMapping(value="/{id}")
    @ResponseStatus(code=HttpStatus.ACCEPTED)
    public Plan actualizarPlan(@PathVariable(name="id")Long id, @RequestBody Plan plan ){
        Optional<Plan> informacion= planRepository.findById(id);
        if(informacion.isPresent()){
            Plan planActualizado= informacion.get();
            planActualizado.setId(id);
            planActualizado.setNombre(plan.getNombre());
            planActualizado.setDescripcion(plan.getDescripcion());
            planActualizado.setTarifa(plan.getTarifa());
            planActualizado.setFechaCreacion(plan.getFechaCreacion());

            Plan planGuardado = planRepository.save(planActualizado);
            return planGuardado;
        }
        return null;
    }


    @DeleteMapping(value="/{id}")
    @ResponseStatus(code=HttpStatus.ACCEPTED)
    public void borrarPlan(@PathVariable(name="id")Long id){
        planRepository.deleteById(id);
    }

    @GetMapping
    @ResponseStatus(code=HttpStatus.OK)
    public Collection<Plan> listarPlanes(){
        Iterable<Plan> planes=planRepository.findAll();

        return (Collection<Plan>)planes;
    }


    @GetMapping(value="/{id}")
    @ResponseStatus(code=HttpStatus.OK)
    public Plan buscarPlan(@PathVariable(name="id")Long id){
        Optional<Plan>buscar= planRepository.findById(id);
        Plan planBuscado =null;
        if(buscar.isPresent()){
            planBuscado= buscar.get();
        }
        return planBuscado;
    }

}
