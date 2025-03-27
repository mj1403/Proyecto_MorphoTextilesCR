/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.controller;

import com.proyecto.domain.Categoria;
import com.proyecto.service.CategoriaService;
import com.proyecto.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/pruebas")
public class PruebasController {

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private ProductoService productoService;

//
    @GetMapping("/listado")
    public String listado(Model model) {
        var lista = categoriaService.getCategorias(false);
        model.addAttribute("categorias", lista);
        var productos = productoService.getProductos(false);
        model.addAttribute("productos", productos);

        return "/pruebas/listado";

    }

//    @GetMapping("/listado/{idCategoria}")
//    public String listado(Model model, Categoria categoria) {
//        var lista = categoriaService.getCategorias(false);
//        model.addAttribute("categorias", lista);
//        categoria = categoriaService.getCategoria(categoria);
//        var productos = categoria.getProductos();
//        model.addAttribute("productos", productos);
//
//        return "/pruebas/listado";
//
//    }

    @GetMapping("/listado2")
    public String listado2(Model model) {
        var productos = productoService.getProductos(false);
        model.addAttribute("productos", productos);
        return "/pruebas/listado2";

    }

//    @PostMapping("/query1")
//    public String query1(
//            @RequestParam("precioInf") double precioInf,
//            @RequestParam("precioSup") double precioSup,
//            Model model) {
//        var productos=productoService.consultaAmpliada(precioInf,precioSup);
//        model.addAttribute("productos", productos);
//        model.addAttribute("precioInf", precioInf);
//        model.addAttribute("precioSup", precioSup);
//        
//        return "/pruebas/listado2";
//    }
//    @PostMapping("/query2")
//    public String query2(
//            @RequestParam("precioInf") double precioInf,
//            @RequestParam("precioSup") double precioSup,
//            Model model) {
//        var productos=productoService.consultaJPQL(precioInf,precioSup);
//        model.addAttribute("productos", productos);
//        model.addAttribute("precioInf", precioInf);
//        model.addAttribute("precioSup", precioSup);
//        
//        return "/pruebas/listado2";
//    }
//    @PostMapping("/query3")
//    public String query3(
//            @RequestParam("precioInf") double precioInf,
//            @RequestParam("precioSup") double precioSup,
//            Model model) {
//        var productos=productoService.consultaSQL(precioInf,precioSup);
//        model.addAttribute("productos", productos);
//        model.addAttribute("precioInf", precioInf);
//        model.addAttribute("precioSup", precioSup);
//        
//        return "/pruebas/listado2";
//    }
    @PostMapping("/query4")
    public String query4(
            @RequestParam("descripcion") String descripcion,
            Model model) {
        var productos = productoService.consultaDescripcion(descripcion);
        model.addAttribute("productos", productos);
        model.addAttribute("descripcion", descripcion);

        return "/pruebas/listado2";
    }

}
