/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.service;

import com.proyecto.domain.Categoria;
import com.proyecto.repository.CategoriaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service 
public class CategoriaService {
    
    @Autowired
    private CategoriaRepository categoriaRepository;
    
    @Transactional(readOnly=true)
    public List<Categoria> getCategorias(boolean activos){
        var lista= categoriaRepository.findAll();
        
        return lista;
        
    }
    
    //crud
    
    @Transactional(readOnly=true)
    public Categoria getCategoria(Categoria categoria){
        
        categoria=categoriaRepository.findById(categoria.getIdCategoria()).orElse(null);
        
        return categoria;
        
    }
    
    @Transactional
    public void delete (Categoria categoria){
        
        categoriaRepository.delete(categoria);
        
    }
    
    @Transactional
    public void save (Categoria categoria){
        categoriaRepository.save(categoria);
    }
}
