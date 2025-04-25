package com.fullstack.soporte.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fullstack.soporte.model.Soporte;
import com.fullstack.soporte.service.SoporteService;

@RestController
public class SoporteController {

    @Autowired
    private SoporteService soporteservice1;

    SoporteService accionesSoporte = new SoporteService();

    @GetMapping("/soportes")
    public List<Soporte> mostrarSoportes(){
        return   accionesSoporte.getAllSoportes();
    }

    @GetMapping("/soportes/{id}")
    public Soporte obtenerSoporte(@PathVariable int id){
        return accionesSoporte.obtenerSoporte(id);
    }

    @PostMapping("/soportes")
    public ResponseEntity<String> crearSoporte(@RequestBody Soporte soporte){
        return ResponseEntity.ok(soporteservice1.crearSoporte(soporte));
    }

    @DeleteMapping("/soportes/{id}")
    public String borrarSoporte(@PathVariable int id){
        return accionesSoporte.borrarSoporte(id);
    }


}
