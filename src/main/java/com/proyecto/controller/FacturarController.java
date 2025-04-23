/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.controller;

import com.proyecto.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/facturar")
public class FacturarController {
     @Autowired
    private ItemService itemService;
    
        //Para facturar los productos del carrito... no implementado...
    @GetMapping("/carrito")
    public String facturarCarrito() {
        itemService.facturar();
        return "redirect:/";
    }
}