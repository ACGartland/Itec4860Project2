package com.example.demo;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class VehicleDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(Vehicle vehicle){
        entityManager.persist(vehicle);
        return;
    }

    public Vehicle getById(int id){
        return entityManager.find(Vehicle.class, id);
    }

    public void update(Vehicle vehicle){
        entityManager.merge(vehicle);
    }

    public void deleteById(int id) {
        Vehicle vehicle = entityManager.find(Vehicle.class, id);

        if (entityManager.contains(vehicle)) {
            entityManager.remove(vehicle);
        }
    }

}
