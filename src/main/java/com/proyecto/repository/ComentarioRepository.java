package com.proyecto.repository;

import com.proyecto.domain.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    
    List<Comentario> findByProductoIdProducto(Long idProducto);
}