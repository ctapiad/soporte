package com.fullstack.soporte.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Soporte {

    private int id;
    private String nombre,detalle,estado;

}
