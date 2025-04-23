/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.controller;

import com.proyecto.domain.Item;
import com.proyecto.domain.Producto;
import com.proyecto.service.ItemService;
import com.proyecto.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/carrito")
public class CarritoController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ProductoService productoService;
    //ModelAndView  hace q no se refresque toda la ventana, sino solo ua parte de la vista, está en verCarrito

    @GetMapping("/agregar/{idProducto}")
    public ModelAndView agregar(Model model, Item item) {
        //se busca si el idProducto ya está eb la lista del carrito
        Item item2 = itemService.getItem(item);

        //si no estaba recupero la información  del producto desde la tabla producto
        if (item2 == null) {
            //recupero la info desde producto
            Producto p = productoService.getProducto(item);
            item2 = new Item(p);
        }
        //se actualiza la lista con el nuevo item
        itemService.save(item2);
        var lista = itemService.getItems();
        var total = itemService.getTotal();

        model.addAttribute("listaItems", lista);
        model.addAttribute("totalProductos", lista.size());//para ver cuantos productos tiene la lista
        model.addAttribute("totalVenta", total);

        return new ModelAndView("/carrito/fragmentos :: verCarrito");
    }

    @GetMapping("/listado")
    public String listado(Model model) {

        var lista = itemService.getItems();//recupera la lista por completo
        var totalProductos = lista.size();
        var totalCompra = itemService.getTotal();
        model.addAttribute("listaItems", lista);
        model.addAttribute("totalProductos", totalProductos);
        model.addAttribute("totalCompra", totalCompra);

        return "/carrito/listado";

    }

    
    @GetMapping("/eliminar/{idProducto}")
    public String eliminar(Model model, Item item) {
        itemService.delete(item);
        return "redirect:/carrito/listado";

    }

    @GetMapping("/modificar/{idProducto}")
    public String modificar(Model model, Item item) {
        item = itemService.getItem(item);
        model.addAttribute("item", item);

        return "/carrito/modifica";

    }

    @PostMapping("/guardar")
    public String guardar( Item item) {
        itemService.update(item);
        return "redirect:/carrito/listado";

    }

}
