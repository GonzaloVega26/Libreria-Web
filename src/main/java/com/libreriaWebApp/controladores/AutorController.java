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
import com.libreriaWebApp.errores.ErrorServicio;
import com.libreriaWebApp.repositorios.AutorRepositorio;
import com.libreriaWebApp.servicios.AutorServicio;

@Controller
@RequestMapping("/autor")
public class AutorController {
	@Autowired
	private AutorRepositorio autorRp;
	@Autowired
	private AutorServicio autorSv;

	@GetMapping("/listaAutores")
	public String listaAutores(Autor autor, ModelMap model) {
		List<Autor> autores = autorRp.findAll();
		model.put("autores", autores);
		return "listaAutores";
	}
	
	@GetMapping("/modificarAutor/{id}")
	public String editarAutor(Autor autor, ModelMap model) {
		autor = autorRp.buscarAutorPorId(autor.getId());
		model.put("autor", autor);
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/autor/listaAutores";
	}

}
