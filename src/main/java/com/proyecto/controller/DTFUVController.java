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
@RequestMapping("/DTFUV")
public class DTFUVController {


    @GetMapping("/informacion")
    public String listado(Model model) {
        String informacion = "DTF UV es una técnica de impresión que permite transferir diseños directamente a superficies rígidas usando una película especial y curado con luz ultravioleta. ";
        model.addAttribute("informacion", informacion);
        return "/DTFUV/informacion";

    }
}
