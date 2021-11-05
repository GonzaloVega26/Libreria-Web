package com.libreriaWebApp.servicios;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.libreriaWebApp.entidades.Autor;
import com.libreriaWebApp.entidades.Editorial;
import com.libreriaWebApp.errores.ErrorServicio;
import com.libreriaWebApp.repositorios.EditorialRepositorio;

@Service
public class EditorialServicio {
	
	@Autowired
	private EditorialRepositorio editorialRp;

	@Transactional
	public void guardarEditorial(String nombre) throws ErrorServicio{
		validacion(nombre);
		Editorial editorial = new Editorial();
		editorial.setNombre(nombre);
		System.out.println(editorial.getId());
		System.out.println(editorial.getNombre());
		System.out.println(editorial.getAlta());
		isRepeated(editorial);
		editorialRp.save(editorial);
		
	}
	
	@Transactional
	public void guardarEditorialObj(Editorial editorial) throws ErrorServicio {
		validacion(editorial.getNombre());
		editorialRp.save(editorial);
	}
	
	private void validacion(String nombre) throws ErrorServicio {
		if(nombre.isBlank() || nombre == null) {
			throw new ErrorServicio("El nombre de la editorial no puede estar vacío");
		}
	}
	
	private void isRepeated(Editorial editorial) throws ErrorServicio {
		Editorial respuesta = editorialRp.buscarEditorialPorNombre(editorial.getNombre());
		if(respuesta != null) {
			throw new ErrorServicio("La editorial ya está registrada");
		}
	}
	
	public List<Editorial> buscarTodasEditoriales() throws ErrorServicio{
		List<Editorial> editoriales = editorialRp.findAll(Sort.by("nombre"));
		Iterator<Editorial> it = editoriales.iterator();
		while (it.hasNext()) {
			Editorial editorial = (Editorial) it.next();
			if(editorial.getAlta() == false) {
				it.remove();
			}
			
		}
		return  editoriales;
	}
	
	public void darBajaEditorial(Editorial editorial) throws ErrorServicio {
		if(editorial == null) {
			throw new ErrorServicio("La editorial no es válido");
		}
		if(editorial.getAlta() == true) {
			editorial.setAlta(false);
		}
		
		editorialRp.save(editorial);
	}
	
	public void darAltaEditorial(Editorial editorial) throws ErrorServicio {
		if(editorial == null) {
			throw new ErrorServicio("La editorial no es válido");
		}
		if(editorial.getAlta() == false) {
			editorial.setAlta(true);
		}
		
		editorialRp.save(editorial);
	}
}
