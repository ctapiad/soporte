package com.fullstack.soporte.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fullstack.soporte.model.Soporte;
import com.fullstack.soporte.model.dto.UsuarioDto;
import com.fullstack.soporte.model.entity.SoporteEntity;
import com.fullstack.soporte.repository.SoporteRepository;

@Service
public class SoporteService {

    @Autowired
    private SoporteRepository soporteRepository;

    @Autowired
    private RestTemplate restTemplate;
    
    

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
                nuevoSoporte.setUser_rut(soporte.getUser_rut());
                nuevoSoporte.setNombre(soporte.getNombre());
                nuevoSoporte.setDetalle(soporte.getDetalle());
                nuevoSoporte.setEstado(soporte.getEstado());
                nuevoSoporte.setFecha_creacion(soporte.getFecha_creacion());
                soportes.add(nuevoSoporte);
            }
    
            return soportes;
    
        } catch (IllegalArgumentException e) {
            System.err.println("Error de validación: " + e.getMessage());
            return new ArrayList<>();
        } catch (Exception e) {
            System.err.println("Error al obtener los soportes: " + e.getMessage());
            return new ArrayList<>();
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
            soporte.setUser_rut(soporteEntity.getUser_rut());
            soporte.setNombre(soporteEntity.getNombre());
            soporte.setDetalle(soporteEntity.getDetalle());
            soporte.setEstado(soporteEntity.getEstado());
            soporte.setFecha_creacion(soporteEntity.getFecha_creacion());
    
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
        Boolean estado = soporteRepository.existsById(soporte.getId());
        if(estado != true){
            String usuarioUrl = "http://http://3.227.212.68:8080/obtenerUsuario/" + soporte.getUser_rut();
            UsuarioDto usuario = restTemplate.getForObject(usuarioUrl, UsuarioDto.class);
            if (usuario == null || usuario.getRut() == null) {
                System.out.println("Usuario no encontrado");
                return null;
            }

            SoporteEntity soporteNuevo = new SoporteEntity();
            soporteNuevo.setUser_rut(usuario.getRut());
            soporteNuevo.setNombre(soporte.getNombre());
            soporteNuevo.setDetalle(soporte.getDetalle());
            soporteNuevo.setEstado(soporte.getEstado());
            soporteNuevo.setFecha_creacion(new Date(System.currentTimeMillis()));
            soporteRepository.save(soporteNuevo);
            return "Soporte creado con exito";
        } else {
            System.out.println("El soporte ya existe");
            return null;
        }
    } catch (Exception e) {
        System.out.println("Error al crear el soporte: " + e.getMessage());
        return null;
    }
}

    
    public String borrarSoporte(int id) {
        try {
            Boolean estado = soporteRepository.existsById(id);
                if(estado == true){
                soporteRepository.deleteById(id);
                return "Soporte eliminado con éxito";
            } else {
                System.out.println("El soporte con el ID especificado no existe");
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar el soporte: " + e.getMessage());
            return null;
        }
    }

    public String modificarSoporte(Soporte soporte) {
        try {
            if (soporteRepository.existsById(soporte.getId())) {
                SoporteEntity soporteExistente = soporteRepository.findById(soporte.getId()).orElse(null);
                if (soporteExistente != null) {
                    //soporteExistente.setUser_rut(soporte.getUser_rut());
                    //
                    soporteExistente.setNombre(soporte.getNombre());
                    soporteExistente.setDetalle(soporte.getDetalle());
                    soporteExistente.setEstado(soporte.getEstado());
                    soporteExistente.setFecha_creacion(soporte.getFecha_creacion());
                    soporteRepository.save(soporteExistente);
                    System.out.println("Soporte editado con éxito");
                    return "";
                } else {
                    System.out.println("El soporte no existe");
                    return null;
                }
            } else {
                System.out.println("El soporte con el ID especificado no existe");
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error al editar el soporte: " + e.getMessage());
            return null;
        }
    }


}
