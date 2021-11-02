package com.libreriaWebApp.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.libreriaWebApp.entidades.Autor;
import com.libreriaWebApp.entidades.Editorial;
import com.libreriaWebApp.entidades.Libro;
import com.libreriaWebApp.errores.ErrorServicio;
import com.libreriaWebApp.repositorios.AutorRepositorio;
import com.libreriaWebApp.repositorios.EditorialRepositorio;
import com.libreriaWebApp.servicios.AutorServicio;
import com.libreriaWebApp.servicios.EditorialServicio;
import com.libreriaWebApp.servicios.LibroServicio;

@Controller
@RequestMapping("/libro")
public class LibroController {
	@Autowired
	LibroServicio libroSv;
	@Autowired
	private AutorRepositorio autorRp;
	@Autowired
	private EditorialRepositorio editorialRp;
	@Autowired
	private AutorServicio autorSv;
	@Autowired
	private EditorialServicio editorialSv;

	@GetMapping("/modificarLibro")
	public String modificarLibro(Libro libro, ModelMap model) {
		List<Autor> autores;
		List<Editorial> editoriales;
		try {
			autores = autorSv.buscarTodosAutores();
			 editoriales = editorialSv.buscarTodasEditoriales();
			model.put("autores", autores);
			model.put("editoriales", editoriales);
			
		} catch (ErrorServicio e) {
			
			e.printStackTrace();
		}
		return "modificarLibro";
		
	}
	
	@PostMapping("/modificarLibro")
	public String registrarLibro(Libro libro) {
		try {
			libroSv.guardarLibroObj(libro);
			return "index.html";
		} catch (Exception e) {
			e.printStackTrace();
			return "registroLibro";
		}
	}
}
