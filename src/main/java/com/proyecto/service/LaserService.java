package com.proyecto.service;

import com.proyecto.domain.Laser;
import com.proyecto.repository.LaserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LaserService {

    @Autowired
    private LaserRepository laserRepository;

    @Transactional(readOnly = true)
    public List<Laser> getLasers(boolean activos) {
        // Puedes usar el parámetro 'activos' si después agregas un campo booleano para eso
        return laserRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Laser getLaser(Laser laser) {
        return laserRepository.findById(laser.getIdLaser()).orElse(null);
    }

    @Transactional
    public void delete(Laser laser) {
        laserRepository.delete(laser);
    }

    @Transactional
    public void save(Laser laser) {
        laserRepository.save(laser);
    }

    @Transactional(readOnly = true)
    public List<Laser> consultaDescripcion(String descripcion) {
        return laserRepository.findByDescripcionContaining(descripcion);
    }
}
