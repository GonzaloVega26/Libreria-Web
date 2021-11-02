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
import com.libreriaWebApp.repositorios.EditorialRepositorio;
import com.libreriaWebApp.servicios.EditorialServicio;

@Controller
@RequestMapping("/editorial")
public class EditorialController {
	
	@Autowired
	EditorialRepositorio editorialRp;
	@Autowired
	EditorialServicio editorialSv;

	@GetMapping("/listaEditoriales")
	public String listaEditoriales(Editorial editorial, ModelMap model) {
		List<Editorial> editoriales = editorialRp.findAll();
		model.put("editoriales", editoriales);
		return "listaEditoriales";
	}
	
	@GetMapping("/modificarEditorial/{id}")
	public String editarEditorial(Editorial editorial, ModelMap model) {
		editorial =editorialRp.buscarEditorialPorId(editorial.getId());
		model.put("editorial", editorial);
		return "modificarEditorial";
	}
	
	@GetMapping("/modificarEditorial")
	public String agregarAutor(Editorial editorial) {
		return "modificarEditorial";
	}
	@PostMapping("/guardarEditorial")
	public String guardar(Editorial editorial) {
		
		try {
			editorialSv.guardarEditorialObj(editorial);
		} catch (ErrorServicio e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/editorial/listaEditoriales";
	}
	
}
