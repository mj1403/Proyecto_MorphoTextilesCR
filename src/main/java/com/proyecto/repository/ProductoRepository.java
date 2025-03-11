
package com.proyecto.repository;

import com.proyecto.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author danid
 */
public interface ProductoRepository extends JpaRepository<Producto, Long>{
    
}

