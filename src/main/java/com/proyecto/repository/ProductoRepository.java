
package com.proyecto.repository;

import com.proyecto.domain.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author danid
 */
public interface ProductoRepository extends JpaRepository<Producto, Long>{
    public List<Producto>
            findByDescripcionContaining(String descripcion);
}

