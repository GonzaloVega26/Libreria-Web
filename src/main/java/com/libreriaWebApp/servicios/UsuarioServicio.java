package com.libreriaWebApp.servicios;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.libreriaWebApp.entidades.Usuario;
import com.libreriaWebApp.enums.Role;
import com.libreriaWebApp.errores.ErrorServicio;
import com.libreriaWebApp.repositorios.UsuarioRepositorio;

@Service
public class UsuarioServicio implements UserDetailsService{
	
	@Autowired
	private UsuarioRepositorio usuarioRp;

	public void guardarUsuario(Usuario usuario) throws ErrorServicio {
		validate(usuario);
		String encriptada = new BCryptPasswordEncoder().encode(usuario.getClave());
		usuario.setClave(encriptada);
		usuarioRp.save(usuario);
	}
	
	private void validate(Usuario usuario) throws ErrorServicio {
		if(usuario.getNombre().isBlank() || usuario.getNombre() == null) {
			throw new ErrorServicio("El nombre no es válido");
		}
		
		if(usuario.getApellido().isBlank() || usuario.getApellido() == null) {
			throw new ErrorServicio("El apellido no es válido");
		}
		
		
		if(usuario.getMail().isBlank() || usuario.getMail() == null) {
			throw new ErrorServicio("El mail no es válido");
		}
		if(usuario.getClave().length() <= 6 || usuario.getClave() == null) {
			throw new ErrorServicio("La clave debe tener al menos 6 caracteres");
		}
		
		
	}

	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
		Usuario usuario = usuarioRp.buscarUsuarioPorMail(mail);
        
        if(usuario == null){
            throw new UsernameNotFoundException(mail);
        }
        var roles = new ArrayList<GrantedAuthority>();
        
        for(Role rol : usuario.getRoles()){
            roles.add(new SimpleGrantedAuthority(rol.name()));
        }
        
        return new User(usuario.getMail(), usuario.getClave(), roles);
		
	}
	
}
