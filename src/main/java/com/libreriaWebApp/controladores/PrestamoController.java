package com.libreriaWebApp.controladores;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.libreriaWebApp.entidades.Libro;
import com.libreriaWebApp.entidades.Prestamo;
import com.libreriaWebApp.errores.ErrorServicio;
import com.libreriaWebApp.servicios.LibroServicio;
import com.libreriaWebApp.servicios.PrestamoServicio;
import com.libreriaWebApp.servicios.UsuarioServicio;

@Controller
@RequestMapping("/prestamo")
public class PrestamoController {

	@Autowired
	private LibroServicio libroSv;
	@Autowired
	private UsuarioServicio usuarioSv;
	@Autowired
	private PrestamoServicio prestamoSv;
	
	@GetMapping("/prestamoLibro/{id}")
	public String prestamoLibros(@PathVariable String id, Prestamo prestamo, ModelMap model ) {
		try {
		
		
			
			model.addAttribute("libro", libroSv.buscaraLibroPorID(id));
			prestamo.setLibro(libroSv.buscaraLibroPorID(id));
		} catch (ErrorServicio e) {
			e.printStackTrace();
			
		}
		return "prestamoLibros";
	}
	
	@PostMapping("/guardarPrestamo")
	public String guardarPrestamo(Prestamo prestamo, Principal user, ModelMap model, Libro libro) {
		
		try {
			prestamo.setUsuario(usuarioSv.buscarUsuarioPorMail(user.getName()));
			
			System.out.println(prestamo);
			prestamoSv.guardarPrestamo(prestamo);
		} catch (ErrorServicio e) {
			e.printStackTrace();
			model.addAttribute("prestamo", prestamo);
			return "prestamoLibros";
		}
		return "index";
	}
}
