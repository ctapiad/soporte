package com.fullstack.soporte.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fullstack.soporte.model.entity.SoporteEntity;

@Repository
public interface SoporteRepository extends JpaRepository<SoporteEntity, Integer>{

    SoporteEntity findByNombre(String nombre);
    Boolean existsByNombre(String nombre);
    void deleteById(int id);
    Boolean findByNombreAndId(String nombre , int id);
    Boolean existsById(int id);
    Boolean existsByNombreAndId(String nombre , int id);
    

    
}

