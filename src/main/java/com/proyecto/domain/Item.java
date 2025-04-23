/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.domain;

import lombok.Data;

/**
 *
 * @author danid
 */
@Data
public class Item extends Producto{
    private int cantidad;

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    public Item() {
       
    }
    //guardamos la info de cuántos items queremos comprar
    public Item(Producto p) {
        
    
        super.setCategoria(p.getCategoria());
        super.setNombre(p.getNombre());
        super.setDescripcion(p.getDescripcion());
        super.setStock(p.getStock());
        super.setIdProducto(p.getIdProducto());
        super.setPrecio(p.getPrecio());
        super.setImagen(p.getImagen());
       
        cantidad=0;
        
    }
    
    
}
