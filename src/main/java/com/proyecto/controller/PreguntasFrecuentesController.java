/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.controller;

import com.proyecto.domain.PreguntasFrecuentes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import java.util.Arrays;

@Controller
public class PreguntasFrecuentesController {
    
      @GetMapping("/preguntasfrecuentes")
    public String verPreguntasFrecuentes(Model model) {
        List<PreguntasFrecuentes> preguntas = Arrays.asList(
            new PreguntasFrecuentes("¿Dónde están ubicados?", "Estamos en San José, Costa Rica. Atendemos con cita previa."),
            new PreguntasFrecuentes("¿Cómo agendo una cita?", "Podés escribirnos por Facebook o WhatsApp y con gusto te agendamos."),
            new PreguntasFrecuentes("¿Qué servicios ofrecen?", "Ofrecemos todo tipo de personalización de camisetas, abrigos, botellas, gorras, tazas y mucho más."),
            new PreguntasFrecuentes("¿Aceptan pagos con tarjeta?", "¡Claro! Aceptamos tarjetas y Sinpe Móvil."),
            new PreguntasFrecuentes("¿Tienen algún descuento para empresas?", "Sí, ofrecemos descuentos especiales para compras al por mayor. Contactanos para más detalles."),
            new PreguntasFrecuentes("¿Hacen envíos a otras provincias?", "Sí, realizamos envíos a todo el país. Los costos y tiempos de entrega varían según la ubicación."),
            new PreguntasFrecuentes("¿Cuál es el tiempo de entrega?", "El tiempo de entrega varía entre 3 y 5 días hábiles, dependiendo de la cantidad y el tipo de producto."),
            new PreguntasFrecuentes("¿Puedo personalizar productos con mi diseño?", "Sí, aceptamos diseños propios. Solo envíanos tu archivo y nosotros lo personalizamos según tus indicaciones."),
            new PreguntasFrecuentes("¿Qué tipo de ropa personalizan?", "Personalizamos camisetas, sudaderas, chaquetas, gorras, y mucho más. ¡Pregúntanos por otros productos!"),
            new PreguntasFrecuentes("¿Tienen tienda física?", "No tenemos tienda física, pero trabajamos solo con citas previas. ¡Contáctanos para más información!")
        );

        model.addAttribute("preguntas", preguntas);
        return "preguntasfrecuentes/faq";

    }
    
    
    
}
