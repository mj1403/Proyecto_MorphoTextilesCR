 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.proyecto.repository;

import com.proyecto.domain.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author danid
 */
public interface RolRepository extends JpaRepository<Rol, Long>{
    
}
