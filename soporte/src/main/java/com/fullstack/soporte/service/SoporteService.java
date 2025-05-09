package com.fullstack.soporte.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullstack.soporte.model.Soporte;
import com.fullstack.soporte.model.entity.SoporteEntity;
import com.fullstack.soporte.repository.SoporteRepository;

@Service
public class SoporteService {

    @Autowired
    private SoporteRepository soporteRepository;
    
    

    public SoporteService(){
     
    }

    public List<Soporte> getAllSoportes() {
        try {
            // Obtener todos los soportes de la base de datos
            List<SoporteEntity> listaSoporte = (List<SoporteEntity>) soporteRepository.findAll();
    
            // Validar si la lista está vacía
            if (listaSoporte.isEmpty()) {
                throw new IllegalArgumentException("No se encontraron soportes en la base de datos.");
            }
    
            // Convertir la lista de SoporteEntity a Soporte
            List<Soporte> soportes = new ArrayList<>();
            for (SoporteEntity soporte : listaSoporte) {
                Soporte nuevoSoporte = new Soporte();
                nuevoSoporte.setId(soporte.getId());
                nuevoSoporte.setNombre(soporte.getNombre());
                nuevoSoporte.setDetalle(soporte.getDetalle());
                nuevoSoporte.setEstado(soporte.getEstado());
                soportes.add(nuevoSoporte);
            }
    
            return soportes;
    
        } catch (IllegalArgumentException e) {
            // Manejo de errores de validación
            System.err.println("Error de validación: " + e.getMessage());
            return new ArrayList<>(); // Devuelve una lista vacía
        } catch (Exception e) {
            // Manejo de errores generales
            System.err.println("Error al obtener los soportes: " + e.getMessage());
            return new ArrayList<>(); // Devuelve una lista vacía
        }
    }

    public Soporte obtenerSoporte(int id) {
        try {
            // Validar que el ID sea positivo
            if (id <= 0) {
                throw new IllegalArgumentException("El ID debe ser un número positivo.");
            }
            // Buscar el soporte en la base de datos
            SoporteEntity soporteEntity = soporteRepository.findById(id).orElse(null);
            // Validar si el soporte existe
            if (soporteEntity == null) {
                throw new IllegalArgumentException("El soporte con el ID especificado no existe.");
            }
            // Convertir SoporteEntity a Soporte
            Soporte soporte = new Soporte();
            soporte.setId(soporteEntity.getId());
            soporte.setNombre(soporteEntity.getNombre());
            soporte.setDetalle(soporteEntity.getDetalle());
            soporte.setEstado(soporteEntity.getEstado());
    
            return soporte;
    
        } catch (IllegalArgumentException e) {
            System.err.println("Error de validación: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.err.println("Error al obtener el soporte: " + e.getMessage());
            return null;
        }
        
    }

    public String crearSoporte(Soporte soporte){
        try {
            Boolean estado = soporteRepository.existsByNombre(soporte.getNombre());
            if(estado != true){
                SoporteEntity soporteNuevo = new SoporteEntity();
                soporteNuevo.setId(soporte.getId());
                soporteNuevo.setNombre(soporte.getNombre());
                soporteNuevo.setDetalle(soporte.getDetalle());
                soporteNuevo.setEstado(soporte.getEstado());
                soporteRepository.save(soporteNuevo);
                return "Soporte creado con exito";
            }
            return null;
        } catch (Exception e) {
            return "Error al crear el soporte";
        }
    }

    
    public String borrarSoporte(int id) {
        try {
            if (soporteRepository.existsById(id)) {
                soporteRepository.deleteById(id);
                return "Soporte eliminado con éxito";
            } else {
                return "El soporte con el ID especificado no existe";
            }
        } catch (Exception e) {
            return "Error al eliminar el soporte: " + e.getMessage();
        }
    }

    public String modificarSoporte(Soporte soporte) {
        try {
            if (soporteRepository.existsById(soporte.getId())) {
                SoporteEntity soporteExistente = soporteRepository.findById(soporte.getId()).orElse(null);
                if (soporteExistente != null) {
                    soporteExistente.setNombre(soporte.getNombre());
                    soporteExistente.setDetalle(soporte.getDetalle());
                    soporteExistente.setEstado(soporte.getEstado());
                    soporteRepository.save(soporteExistente);
                    return "Soporte editado con éxito";
                } else {
                    return "El soporte no existe";
                }
            } else {
                return "El soporte con el ID especificado no existe";
            }
        } catch (Exception e) {
            return "Error al editar el soporte: " + e.getMessage();
        }
    }

}
