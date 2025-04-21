
package com.proyecto.service;

import com.proyecto.domain.Usuario;

import jakarta.mail.MessagingException;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

@Service
public class RegistroService {

    @Autowired
    private EmailService emailService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private FirebaseStorageService firebaseStorageService;

    public Model activar(Model model, String username, String clave) {
        Usuario usuario
                = usuarioService.getUsuarioPorUsernameYPassword(username,
                        clave);
        if (usuario != null) {
            model.addAttribute("usuario", usuario);
        } else {
            model.addAttribute(
                    "titulo",
                    messageSource.getMessage(
                            "registro.activar",
                            null, Locale.getDefault()));
            model.addAttribute(
                    "mensaje",
                    messageSource.getMessage(
                            "registro.activar.error",
                            null, Locale.getDefault()));
        }
        return model;
    }

    public void activar(Usuario usuario, MultipartFile imagenFile) {
        var codigo = new BCryptPasswordEncoder();
        usuario.setPassword(codigo.encode(usuario.getPassword()));

        if (!imagenFile.isEmpty()) {
            usuarioService.save(usuario, false);
            usuario.setRutaImagen(
                    firebaseStorageService.cargaImagen(
                            imagenFile,
                            "usuarios",
                            usuario.getIdUsuario()));
        }
        usuarioService.save(usuario, true);
    }

    public Model crearUsuario(Model model, Usuario usuario)
            throws MessagingException {
        String mensaje;
        if (!usuarioService.
                existeUsuarioPorUsernameOEmail(
                        usuario.getUsername(),
                        usuario.getEmail())) {
            String clave = demeClave();
            usuario.setPassword(clave);
            usuario.setActivo(false);
            usuarioService.save(usuario, false);
            enviaEmailActivar(usuario);
            mensaje = String.format(
                    messageSource.getMessage(
                            "registro.mensaje.activacion.ok",
                            null,
                            Locale.getDefault()),
                    usuario.getEmail());
        } else {
            mensaje = String.format(
                    messageSource.getMessage(
                            "registro.mensaje.usuario.o.email",
                            null,
                            Locale.getDefault()),
                    usuario.getUsername(), usuario.getEmail());
        }
        model.addAttribute(
                "titulo",
                messageSource.getMessage(
                        "registro.activar",
                        null,
                        Locale.getDefault()));
        model.addAttribute(
                "mensaje",
                mensaje);
        return model;
    }

    public Model recordarUsuario(Model model, Usuario usuario)
            throws MessagingException {
        String mensaje;
        Usuario usuario2 = usuarioService.getUsuarioPorUsernameOEmail(
                usuario.getUsername(),
                usuario.getEmail());
        if (usuario2 != null) {
            String clave = demeClave();
            usuario2.setPassword(clave);
            usuario2.setActivo(false);
            usuarioService.save(usuario2, false);
            enviaEmailRecordar(usuario2);
            mensaje = String.format(
                    messageSource.getMessage(
                            "registro.mensaje.recordar.ok",
                            null,
                            Locale.getDefault()),
                    usuario.getEmail());
        } else {
            mensaje = String.format(
                    messageSource.getMessage(
                            "registro.mensaje.usuario.o.email",
                            null,
                            Locale.getDefault()),
                    usuario.getUsername(), usuario.getEmail());
        }
        model.addAttribute(
                "titulo",
                messageSource.getMessage(
                        "registro.activar",
                        null,
                        Locale.getDefault()));
        model.addAttribute(
                "mensaje",
                mensaje);
        return model;
    }

    private String demeClave() {
        String tira = "ABCDEFGHIJKLMNOPQRSTUXYZabcdefghijklmnopqrstuvwxyz0123456789";
        String clave = "";
        for (int i = 0; i < 40; i++) {
            clave += tira.charAt((int) (Math.random() * tira.length()));
        }
        return clave;
    }

    //Ojo cómo le lee una informacion del application.properties
    @Value("${servidor.http}")
    private String servidor;

    private void enviaEmailActivar(Usuario usuario) throws MessagingException {
        String mensaje = messageSource.getMessage(
                "registro.email.activar",
                null, Locale.getDefault());
        mensaje = String.format(
                mensaje, usuario.getNombre(),
                usuario.getApellido(), servidor,
                usuario.getUsername(), usuario.getPassword());
        String asunto = messageSource.getMessage(
                "registro.mensaje.activacion",
                null, Locale.getDefault());
        emailService.enviarEmailHtml(usuario.getEmail(), asunto, mensaje);
    }

    private void enviaEmailRecordar(Usuario usuario) throws MessagingException {
        String mensaje = messageSource.getMessage(""
                + "registro.email.recordar",
                null,
                Locale.getDefault());
        mensaje = String.format(
                mensaje, usuario.getNombre(),
                usuario.getApellido(), servidor,
                usuario.getUsername(), usuario.getPassword());
        String asunto = messageSource.getMessage(
                "registro.mensaje.recordar",
                null, Locale.getDefault());
        emailService.enviarEmailHtml(
                usuario.getEmail(),
                asunto, mensaje);
    }
}

