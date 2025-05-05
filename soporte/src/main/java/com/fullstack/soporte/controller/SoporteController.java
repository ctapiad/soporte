package com.fullstack.soporte.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fullstack.soporte.model.Soporte;
import com.fullstack.soporte.service.SoporteService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class SoporteController {

    @Autowired
    private SoporteService soporteservice1;

    SoporteService accionesSoporte = new SoporteService();

    @Operation (summary = "Este endpoint permite obtener todos los soportes")
    @GetMapping("/soportes")
    public ResponseEntity <List<Soporte>>mostrarSoportes(){
        if(soporteservice1.getAllSoportes() != null){
            return ResponseEntity.ok(soporteservice1.getAllSoportes());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/soportes/{id}")
    public ResponseEntity<Soporte> obtenerSoporte(@PathVariable int id){
        if (soporteservice1.obtenerSoporte(id) != null) {
            return ResponseEntity.ok(soporteservice1.obtenerSoporte(id));
        } 
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/soportes")
    public ResponseEntity<String> crearSoporte(@RequestBody Soporte soporte){
        if (soporteservice1.crearSoporte(soporte) != null) {
            return ResponseEntity.ok(soporteservice1.crearSoporte(soporte));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/soportes/{id}")
    public ResponseEntity<String> borrarSoporte(@PathVariable int id){
        if(soporteservice1.borrarSoporte(id) != null){
            return ResponseEntity.ok(soporteservice1.borrarSoporte(id));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/soportes")
    public ResponseEntity<String> modificarSoporte(@RequestBody Soporte soporte){
        if(soporteservice1.modificarSoporte(soporte) != null){
            return ResponseEntity.ok(soporteservice1.modificarSoporte(soporte));
        }
        return ResponseEntity.notFound().build();
    }


}
