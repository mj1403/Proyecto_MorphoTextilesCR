
package com.proyecto.repository;

import com.proyecto.domain.Laser;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author danid
 */
public interface LaserRepository extends JpaRepository<Laser, Long>{
    public List<Laser>
            findByDescripcionContaining(String descripcion);
}

