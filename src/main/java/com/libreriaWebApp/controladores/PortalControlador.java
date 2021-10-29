package com.libreriaWebApp.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.libreriaWebApp.entidades.Autor;
import com.libreriaWebApp.entidades.Editorial;
import com.libreriaWebApp.errores.ErrorServicio;
import com.libreriaWebApp.repositorios.AutorRepositorio;
import com.libreriaWebApp.repositorios.EditorialRepositorio;
import com.libreriaWebApp.servicios.AutorServicio;
import com.libreriaWebApp.servicios.EditorialServicio;
import com.libreriaWebApp.servicios.LibroServicio;

@Controller
@RequestMapping("/")
public class PortalControlador {
	@Autowired
	private AutorServicio autorSv;
	@Autowired
	private EditorialServicio editorialSv;
	@Autowired
	private LibroServicio libroSv;
	@Autowired
	private AutorRepositorio autorRp;
	@Autowired
	private EditorialRepositorio editorialRp;
	
	@GetMapping("/index.html")
	public String index() {
		return "index.html";
	}

	@GetMapping("/registroAutor.html")
	public String registroAutor() {
		return "registroAutor.html";
	}
	
	@GetMapping("/registroEditorial.html")
	public String registroEditorial() {
		
		return "registroEditorial.html";
	}
	
	@GetMapping("/registroLibro.html")
	public String registroLibro(ModelMap model) {
		List<Autor> autores= autorRp.findAll();
		List<Editorial> editoriales = editorialRp.findAll();
		model.put("autores", autores);
		model.put("editoriales", editoriales);
		return "registroLibro.html";
	}
	
	@PostMapping("/registrarAutor")
	public String registarAutor(@RequestParam String nombre, @RequestParam String apellido, ModelMap model) {
		try {
			autorSv.guardarAutor(nombre, apellido);
		} catch (ErrorServicio e) {
			e.printStackTrace();
		model.put("nombre", nombre);
		model.put("apellido", apellido);
		return "registroAutor.html";
		}
		return "index.html";
	}
	
	@PostMapping("/registrarEditorial")
	public String registrarEditorial(@RequestParam String nombre, ModelMap model) {
		try {
			editorialSv.guardarEditorial(nombre);
		} catch (ErrorServicio e) {
			e.printStackTrace();
			model.put("nombre", nombre);
			return "registroEditorial.html";
		}
		return "index.html";
	}
	
	@PostMapping("/registrarLibro")
	public String registrarLibro(@RequestParam String isbn, @RequestParam String nombre,
			@RequestParam Integer anio, @RequestParam Integer ejemplares, 
			@RequestParam Integer ejemplaresPrestados, @RequestParam Integer ejemplaresRestantes,
			@RequestParam String idAutor, @RequestParam String idEditorial) {
		try {
			libroSv.guardarLibro(isbn, nombre, anio, ejemplares, ejemplaresPrestados, ejemplaresRestantes, idAutor, idEditorial);
			return "index.html";
		} catch (Exception e) {
			e.printStackTrace();
			return "registroLibro.html";
		}
	}
	
}
