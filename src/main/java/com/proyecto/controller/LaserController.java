package com.proyecto.controller;

import com.proyecto.domain.Laser;
import com.proyecto.service.LaserService;
import com.proyecto.service.FirebaseStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/laser")
public class LaserController {

    @Autowired
    private LaserService laserService;

    @GetMapping("/listado")
    public String listado(Model model) {
        var lista = laserService.getLasers(false);
        model.addAttribute("lasers", lista);
        return "/laser/listado";
    }

    @GetMapping("/eliminar/{idLaser}")
    public String eliminar(Laser laser) {
        laserService.delete(laser);
        return "redirect:/laser/listado";
    }

    @GetMapping("/modificar/{idLaser}")
    public String modificar(Laser laser, Model model) {
        laser = laserService.getLaser(laser);
        model.addAttribute("laser", laser);
        return "/laser/modifica";
    }

    @Autowired
    private FirebaseStorageService firebaseStorageService;

    @PostMapping("/guardar")
    public String save(Laser laser,
                       @RequestParam("imagenFile") MultipartFile imagenFile) {
        if (!imagenFile.isEmpty()) {
            laserService.save(laser); // primero guarda para generar ID
            String ruta = firebaseStorageService.cargaImagen(imagenFile, "laser", laser.getIdLaser());
            laser.setImagen(ruta);
        }
        laserService.save(laser);
        return "redirect:/laser/listado";
    }
}
