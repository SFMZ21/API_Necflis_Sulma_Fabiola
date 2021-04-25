package com.example.demo.repositories;

import com.example.demo.entities.Plan;
import org.springframework.data.repository.CrudRepository;

public interface PlanRepository extends CrudRepository<Plan,Long> {
}
