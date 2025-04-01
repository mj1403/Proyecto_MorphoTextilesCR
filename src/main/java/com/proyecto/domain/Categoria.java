/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name="categoria")
public class Categoria implements Serializable {
    private static final long serialVersionUID=1L;
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="id_categoria")
    private Long idCategoria;
    private String descripcion;
    private String imagen;

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getimagen() {
        return imagen;
    }

    public void setimagen(String imagen) {
        this.imagen = imagen;
    }
}
