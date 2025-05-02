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

    public List<Soporte> getAllSoportes(){
        //soporteRepository.findAll();
        List<SoporteEntity> listaSoporte = (List<SoporteEntity>) soporteRepository.findAll();
        List<Soporte> soportes = new ArrayList<>();
        
        for(SoporteEntity soporte : listaSoporte){
            Soporte nuevoSoporte = new Soporte();
            nuevoSoporte.setId(soporte.getId());
            nuevoSoporte.setNombre(soporte.getNombre());
            nuevoSoporte.setDetalle(soporte.getDetalle());
            nuevoSoporte.setEstado(soporte.getEstado());
            soportes.add(nuevoSoporte);
        }
        return soportes;
    }

    public Soporte obtenerSoporte(int id){
        List<Soporte> soportes2 = getAllSoportes();
        for(Soporte sop : soportes2){
            if(sop.getId() == id){
                return sop;
            }
        }
        return null;
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
            return "El soporte ya existe";
        } catch (Exception e) {
            return "Error al crear el soporte";
        }
    }

    
    public String borrarSoporte(int id){
        List<Soporte> soportes3 = getAllSoportes();
        for(Soporte sop : soportes3){
            if(sop.getId() == id){
                soportes3.remove(sop);
                return "Soporte eliminado con exito";
        }
    }
    return null;
    }

}
