package com.proyecto.service;


import com.proyecto.domain.Rol;
import com.proyecto.domain.Usuario;
import com.proyecto.repository.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")

public class UsuarioDatailsService 
        implements UserDetailsService{
    @Autowired
    private UsuarioRepository usuarioRepository; 
    @Autowired
    private HttpSession session;

    @Override
    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String username) 
            throws UsernameNotFoundException {
        //Se busca el usuario vía el username
        Usuario usuario = usuarioRepository.findByUsername(username);
        
        //Se valida si se encontró el usuario
        if(usuario == null){
            //No se encontró el usuario...
            throw new UsernameNotFoundException(username);
        }
        
        //Si estamos acá es que se encontró el usuario en la tabla de la BD
        //Se carga la imagen del usuario en una variable de session. 
        session.removeAttribute("usuarioImagen");
        session.setAttribute("usuarioImagen", usuario.getRutaImagen());
        
        //Se procede a cargar los "roles" en un arreglo de Roles de Seguridad...
        var roles = new ArrayList<GrantedAuthority>();
        
        //Se recorre el arraylist de los roles del usuario
        for (Rol rol:usuario.getRoles()){
            roles.add(new SimpleGrantedAuthority("ROLE_"+rol.getNombre()));
        }
        
        //Se devuelve un usuario con la información de username, password y roles...
        
        return new User(usuario.getUsername(),usuario.getPassword(), roles); 
    }

    
}
