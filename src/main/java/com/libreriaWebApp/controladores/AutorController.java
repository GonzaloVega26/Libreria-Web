package com.libreriaWebApp.controladores;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.libreriaWebApp.entidades.Autor;
import com.libreriaWebApp.entidades.Usuario;
import com.libreriaWebApp.enums.Role;
import com.libreriaWebApp.errores.ErrorServicio;
import com.libreriaWebApp.repositorios.AutorRepositorio;
import com.libreriaWebApp.servicios.AutorServicio;
import com.libreriaWebApp.servicios.UsuarioServicio;

@Controller
@RequestMapping("/autor")
public class AutorController {
	@Autowired
	private AutorRepositorio autorRp;
	@Autowired
	private AutorServicio autorSv;
	@Autowired
	private UsuarioServicio usuarioSv;

	@GetMapping("/listaAutores")
	public String listaAutores(Autor autor, ModelMap model, Principal user) {
		List<Autor> autores;
		Usuario usuario = usuarioSv.buscarUsuarioPorMail(user.getName());
		try {
			System.out.println(usuario.getRoles().contains(Role.ROLE_ADMIN));
			if(usuario.getRoles().contains(Role.ROLE_ADMIN)) {
				
				autores = autorSv.buscarTodosAutores();
				
				model.put("autores", autores);
			}else {
				autores = autorSv.buscarTodosAutoresActivos();
				model.put("autores", autores);
			}
			
			
		} catch (ErrorServicio e) {
			e.printStackTrace();
		}
		
		return "listaAutores";
	}
	
	@GetMapping("/modificarAutor/{id}")
	public String editarAutor(Autor autor, ModelMap model) {
		try {
			System.out.println(autor);
			autor = autorRp.buscarAutorPorId(autor.getId());
			autor.getLibrosEscritos().size();
			System.out.println(autor);
			model.put("autor", autor);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		return "modificarAutor";
	}
	
	@GetMapping("/modificarAutor")
	public String agregarAutor(Autor autor) {
		return "modificarAutor";
	}
	@PostMapping("/guardarAutor")
	public String guardar(Autor autor) {
		
		try {
			autorSv.guardarAutorObj(autor);
		} catch (ErrorServicio e) {
			
			e.printStackTrace();
		}
		return "redirect:/autor/listaAutores";
	}
	
	@GetMapping("/darBaja/{id}")
	public String darBajaAutor(Autor autor) {
		
		try {
			autor = autorRp.buscarAutorPorId(autor.getId());
			autor.setAlta(false);
			
			autorSv.guardarAutorObj(autor);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/autor/listaAutores" ;
	}
	
	@GetMapping("/darAlta/{id}")
	public String darAltaAutor(Autor autor) {
		
		try {
			autor = autorRp.buscarAutorPorId(autor.getId());
			autor.setAlta(true);
			
			autorSv.guardarAutorObj(autor);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/autor/listaAutores" ;
	}

}
