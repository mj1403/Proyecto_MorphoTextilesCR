/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/DFTUV")
public class DFTUVController {


    @GetMapping("/informacion")
    public String listado(Model model) {
        String informacion = "DFT UV es una técnica de impresión que permite";
        model.addAttribute("informacion", informacion);
        return "/DFTUV/informacion";

    }
}
