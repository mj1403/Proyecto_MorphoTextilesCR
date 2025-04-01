package com.proyecto.controller;

import com.proyecto.domain.Comentario;
import com.proyecto.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/comentarios")
public class ComentarioController {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @GetMapping("/producto/{idProducto}")
    public String obtenerComentariosPorProducto(@PathVariable Long idProducto, Model model) {
        List<Comentario> comentarios = comentarioRepository.findByProductoIdProducto(idProducto);
        model.addAttribute("comentarios", comentarios);
        model.addAttribute("idProducto", idProducto);
        return "comentarios/lista";
    }

    @PostMapping("/guardar")
    public String guardarComentario(@ModelAttribute Comentario comentario) {
        comentarioRepository.save(comentario);
        return "redirect:/comentarios/producto/" + comentario.getProducto().getIdProducto();
    }

    @PostMapping("/eliminar/{idComentario}")
    public String eliminarComentario(@PathVariable Long idComentario) {
        Optional<Comentario> comentarioOpt = comentarioRepository.findById(idComentario);
        if (comentarioOpt.isPresent()) {
            Long idProducto = comentarioOpt.get().getProducto().getIdProducto();
            comentarioRepository.deleteById(idComentario);
            return "redirect:/comentarios/producto/" + idProducto;
        }
        return "redirect:/";
    }
}