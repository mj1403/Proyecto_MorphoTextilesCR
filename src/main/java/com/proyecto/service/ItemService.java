/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.service;

import com.proyecto.domain.Factura;
import com.proyecto.domain.Item;
import com.proyecto.domain.Usuario;
import com.proyecto.domain.Venta;
import com.proyecto.repository.FacturaRepository;
import com.proyecto.repository.ProductoRepository;
import com.proyecto.repository.UsuarioRepository;
import com.proyecto.repository.VentaRepository;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    //se usa HttpSession para una variable de session
    @Autowired
    private HttpSession session;

    //se guarda un item en la lista variuable ed session si noe xiste la lista, se crea
    public void save(Item item) {
        @SuppressWarnings("unchecked")
        //recuperar la variable de session
        List<Item> lista = (List) session.getAttribute("listaItems");

        //se valida si la lsita es ula para hacerña desde cero
        if (lista == null) {
            lista = new ArrayList<>();
        }
        //se valida si el diProducto ya está en la lisra.. si es así se se suma 1 a la cantidad
        boolean existe = false;
        for (Item i : lista) {
            if (Objects.equals(i.getIdProducto(), item.getIdProducto())) {//como son long se comparan con equals no con ==
                existe = true;
                // se valida si aún hay existencias disponibles
                if (i.getStock() < i.getCantidad()) {
                    i.setCantidad(i.getCantidad() + 1);
                }
                break;
            }
        }
        // si no existe se agrega y si sí existe se cmbia la cantidad
        if (!existe) {
            item.setCantidad(1);
            lista.add(item);
        }
        //se crea/actualiza la variable de session "listaItems"
        session.setAttribute("listaItems", lista);
    }
    //se recupera un item en la lista  

    public Item getItem(Item item) {
        @SuppressWarnings("unchecked")
        List<Item> lista = (List) session.getAttribute("listaItems");

        if (lista == null) {
            return null;
        }
        //se busca el item en la lista
        for (Item i : lista) {
            if (Objects.equals(i.getIdProducto(), item.getIdProducto())) {//como son long se comparan con equals no con ==
                    return i;             
            }
        }
        //si no se encontró el item retorna null
        return null;
    }

    //se calcula el total de productos en la lista
    public double getTotal() {
        @SuppressWarnings("unchecked")
        List<Item> lista = (List) session.getAttribute("listaItems");
        double total = 0;

        if (lista != null) {
            for (Item i : lista) {
                total += i.getCantidad() * i.getPrecio();
            }
        }
        return total;
    }

    public List<Item> getItems() {
        @SuppressWarnings("unchecked")
        List<Item> lista = (List) session.getAttribute("listaItems");

        return lista;

    }

    //se elimina un item de la variable de session
    public void delete(Item item) {
        @SuppressWarnings("unchecked")
        //recuperar la variable de session
        List<Item> lista = (List) session.getAttribute("listaItems");

        //
        if (lista != null) {

            boolean existe = false;
            int posicion = -1;
            for (Item i : lista) {
                posicion++;
                if (Objects.equals(i.getIdProducto(), item.getIdProducto())) {
                    existe = true;
                    break;
                }
            }
            if (existe) {
                lista.remove(posicion);
                session.setAttribute("listaItems", lista);
            }
        }
    }
    
    //se actualiza la cant de un item de la variable de session
    public void update(Item item) {
        @SuppressWarnings("unchecked")
        //recuperar la variable de session
        List<Item> lista = (List) session.getAttribute("listaItems");

        //se valida q la lsita exista
        if (lista != null) {
            for (Item i : lista) {
            
                if (Objects.equals(i.getIdProducto(), item.getIdProducto())) {
                   i.setCantidad(item.getCantidad());
                    break;
                }
            }
        }
    }
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private FacturaRepository facturaRepository;
    @Autowired
    private VentaRepository ventaRepository;
    
    public void facturar() {
        //Se debe recuperar el usuario autenticado y obtener su idUsuario
        String username = "";
        var principal = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        if (principal instanceof UserDetails userDetails) {
            username = userDetails.getUsername();
        } else {
            if (principal != null) {
                username = principal.toString();
            }
        }

        if (username.isBlank()) {
            System.out.println("username en blanco...");
            return;
        }

        Usuario usuario = usuarioRepository.findByUsername(username);
        if (usuario == null) {
            System.out.println("Usuario no existe en usuarios...");
            return;
        }

        //Se debe registrar la factura incluyendo el usuario
        Factura factura = new Factura(usuario.getIdUsuario());
        factura = facturaRepository.save(factura);

        //Se debe registrar las ventas de cada producto -actualizando existencias-
        @SuppressWarnings("unchecked")
        List<Item> listaItems = (List) session.getAttribute("listaItems");
        if (listaItems != null) {
            double total = 0;
            for (Item i : listaItems) {
                var producto = productoRepository.getReferenceById(i.getIdProducto());
                if (producto.getStock() >= i.getCantidad()) {
                    Venta venta = new Venta(factura.getIdFactura(),
                            i.getIdProducto(),
                            i.getPrecio(),
                            i.getCantidad());
                    ventaRepository.save(venta);
                    producto.setStock(producto.getStock() - i.getCantidad());
                    productoRepository.save(producto);
                    total += i.getCantidad() * i.getPrecio();
                }
            }

            //Se debe registrar el total de la venta en la factura
            factura.setTotal(total);
            facturaRepository.save(factura);

            //Se debe limpiar el carrito la lista...
            listaItems.clear();
        }
    }

    
}
