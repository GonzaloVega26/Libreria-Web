package com.libreriaWebApp.servicios;

import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.libreriaWebApp.entidades.Autor;
import com.libreriaWebApp.entidades.Libro;
import com.libreriaWebApp.errores.ErrorServicio;
import com.libreriaWebApp.repositorios.AutorRepositorio;

@Service
public class AutorServicio {
	@Autowired
	private AutorRepositorio autorRp;
	
	@Transactional
	public void guardarAutor(String nombre, String apellido) throws ErrorServicio{
		validate(nombre, apellido);
		Autor autor = new Autor();
		autor.setNombre(nombre);
		autor.setApellido(apellido);
		isRepetead(autor);
		autorRp.save(autor);
	}
	
	@Transactional
	public void guardarAutorObj(Autor autor) throws ErrorServicio {
		validate(autor.getNombre(), autor.getApellido());
		autorRp.save(autor);
	}
	@Transactional
	public void agregarLibroToAutor(Libro libro) {
		Autor autor = libro.getAutor();
		autor.getLibrosEscritos().add(libro);
		
		autorRp.save(autor);
	}
	
	private void validate(String nombre, String apellido) throws ErrorServicio {
		if(nombre.isBlank() || nombre == null) {
			throw new ErrorServicio("El nombre del autor no puede ser nulo");
		}
		if(apellido.isBlank() || apellido == null) {
			throw new ErrorServicio("El apellido del autor no puede ser nulo");
		}
	}
	
	private void isRepetead(Autor autor) throws ErrorServicio {
		Autor respuesta = autorRp.buscarAutorPorNombreApellido(autor.getNombre(), autor.getApellido());
		if(respuesta != null) {
			throw new ErrorServicio("El autor ya está registrado");
		}
	}
	
	public List<Autor> buscarTodosAutores() throws ErrorServicio{
		List<Autor> autores = autorRp.findAll(Sort.by("nombre"));
		Iterator<Autor> it = autores.iterator();
		while (it.hasNext()) {
			Autor autor = (Autor) it.next();
			if(autor.getAlta() == false) {
				it.remove();
			}
		}
		
		return  autores;
	}
	
	public void darBajaAutor(Autor autor) throws ErrorServicio {
		if(autor == null) {
			throw new ErrorServicio("El autor no es válido");
		}
		if(autor.getAlta() == true) {
			autor.setAlta(false);
		}
		
		autorRp.save(autor);
	}
	
	public void darAltaAutor(Autor autor) throws ErrorServicio {
		if(autor == null) {
			throw new ErrorServicio("El autor no es válido");
		}
		if(autor.getAlta() == false) {
			autor.setAlta(true);
		}
		
		autorRp.save(autor);
	}
	
}
