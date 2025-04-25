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
    
    private final List<Soporte> soportes = new ArrayList<>();

    public SoporteService(){
        soportes.add(new Soporte(1,"nombre","detalle","estado"));
    }

    public List<Soporte> getAllSoportes(){
        return soportes;
    }

    public Soporte obtenerSoporte(int id){
        for(Soporte sop : soportes){
            if(sop.getId() == id){
                return sop;
            }
        }
        return null;
    }

    public String crearSoporte(Soporte soporte){
        try {
            Boolean estado = soporteRepository.existByNombre(soporte.getNombre());
            if(estado != true){
                SoporteEntity soporteNuevo = new SoporteEntity();
                soporteNuevo.setId(soporte.getId());
                soporteNuevo.setNombre(soporte.getNombre());
                soporteNuevo.setDetalle(soporte.getDetalle());
                soporteNuevo.setEstado(soporte.getEstado());
                return "Soporte creado con exito";
            }
            return "El soporte ya existe";
        } catch (Exception e) {
            return "Error al crear el soporte";
        }
    }

    
    public String borrarSoporte(int id){
        for(Soporte sop : soportes){
            if(sop.getId() == id){
                soportes.remove(sop);
                return "Soporte eliminado con exito";
        }
    }
    return null;
    }

}
