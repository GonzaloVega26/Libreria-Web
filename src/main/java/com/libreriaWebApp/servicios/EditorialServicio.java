package com.libreriaWebApp.servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
