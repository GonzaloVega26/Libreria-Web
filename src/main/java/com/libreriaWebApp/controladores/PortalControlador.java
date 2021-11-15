package com.libreriaWebApp.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.libreriaWebApp.entidades.Usuario;
import com.libreriaWebApp.errores.ErrorServicio;
import com.libreriaWebApp.servicios.EmailSenderServicio;
import com.libreriaWebApp.servicios.UsuarioServicio;

@Controller
@RequestMapping("/")
public class PortalControlador {
	
	@Autowired
	UsuarioServicio usuarioSv;
	
	@Autowired EmailSenderServicio emailSenderSv;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/registroUsuario")
	public String registroUser(Usuario usuario){
		
		return "registroUsuario";
	}
	
	@PostMapping("/guardarUsuario")
	public String guardarUser(Usuario usuario) {
		try {
			usuarioSv.guardarUsuario(usuario);
			emailSenderSv.sendSimpleMail(usuario.getMail(), "Gracias por registrarte",
					"Spring Boot mailSender");
		} catch (ErrorServicio e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "registroUsuario";
		}
		return "index";
	}
	
	
}
