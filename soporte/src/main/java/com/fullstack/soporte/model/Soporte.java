package com.fullstack.soporte.model;



import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Soporte {

    private int id,user_id;
    private String nombre,detalle,estado;
    private Date fecha_creacion;
    

}
