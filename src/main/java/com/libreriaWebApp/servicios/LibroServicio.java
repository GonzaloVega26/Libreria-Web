package com.libreriaWebApp.servicios;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.libreriaWebApp.entidades.Autor;
import com.libreriaWebApp.entidades.Editorial;
import com.libreriaWebApp.entidades.Libro;
import com.libreriaWebApp.errores.ErrorServicio;
import com.libreriaWebApp.repositorios.AutorRepositorio;
import com.libreriaWebApp.repositorios.EditorialRepositorio;
import com.libreriaWebApp.repositorios.LibroRepositorio;

@Service
public class LibroServicio {

	@Autowired
	private AutorRepositorio autorRp;
	@Autowired
	private LibroRepositorio libroRp;
	@Autowired
	private EditorialRepositorio editorialRp;
	@Autowired
	private AutorServicio autorSv;

	@Transactional
	public void guardarLibro(String isbn, String nombre, Integer anio, Integer ejemplares, Integer ejemplaresPrestados,
			Integer ejemplaresRestantes, String  idAutor, String idEditorial) throws ErrorServicio {
		validacion(isbn, nombre, anio, ejemplares, ejemplaresPrestados, ejemplaresRestantes);
		Optional<Autor> rp1 = autorRp.findById(idAutor);
		Optional<Editorial> rp2 = editorialRp.findById(idEditorial);
		if(rp1.isPresent() && rp2.isPresent() ) {
			Autor autor = rp1.get();
			Editorial editorial = rp2.get();
			Libro libro = new Libro(isbn, nombre, anio, ejemplares, ejemplaresPrestados, ejemplaresRestantes, autor,
					editorial);

			libroRp.save(libro);
			autorSv.agregarLibroToAutor(libro);
			
		} else {
			throw new ErrorServicio("El autor y/o la editorial no son válidos");
		}
	
	}
	
	@Transactional
	public void guardarLibroObj(Libro libro) throws ErrorServicio {
		validacion(libro.getIsbn(),libro.getNombre(),libro.getAnio(),libro.getEjemplares(),
				libro.getEjemplaresPrestados(), libro.getEjemplaresRestantes());
		Optional<Autor> rp1 = autorRp.findById(libro.getAutor().getId());
		Optional<Editorial> rp2 = editorialRp.findById(libro.getEditorial().getId());
		if(rp1.isPresent() && rp2.isPresent() ) {
			libroRp.save(libro);
			autorSv.agregarLibroToAutor(libro);
			
		} else {
			throw new ErrorServicio("El autor y/o la editorial no son válidos");
		}
	}
	
	@Transactional(readOnly = true)
	public List<Libro> buscarAllLibrosPorAlta() throws ErrorServicio{
		
		return libroRp.buscarLibroPorAlta(true);
	}
	@Transactional(readOnly = true)
	public Libro buscaraLibroPorID(String id) throws ErrorServicio{
		Optional<Libro> respuesta = libroRp.findById(id);
		if(respuesta.isPresent()) {
			return respuesta.get();
		}else {
			throw new ErrorServicio("Libro no válido");
		}
				
	}


	private void validacion(String isbn, String nombre, Integer anio, Integer ejemplares, Integer ejemplaresPrestados,
			Integer ejemplaresRestantes) throws ErrorServicio {
		if (isbn.isBlank() || isbn == null) {
			throw new ErrorServicio("El isbn no puede estar vacío");
		}
		if (isbn.length() < 13) {
			throw new ErrorServicio("El isbn tiene que tener al menos 13 dígitos");
		}
		if (ejemplares < 0 || ejemplares == null) {
			throw new ErrorServicio("El número de ejemplares no puede ser cero/vacío");
		}
		if (ejemplaresPrestados < 0 || ejemplaresPrestados == null) {
			throw new ErrorServicio("El número de ejemplares Prestados no puede ser cero/vacío");
		}
		if (ejemplaresRestantes < 0 || ejemplaresRestantes == null) {
			throw new ErrorServicio("El número de ejemplares Restantes no puede ser cero/vacío");
		}

		if (anio == null) {
			throw new ErrorServicio("El año de publicacion no puede ser vacío");
		}
		if (anio > LocalDate.now().getYear() + 1) {
			throw new ErrorServicio("No puedes publicar un libro que todavía no se escribe ;P");
		}

	}
	
	public void prestarLibro(Libro libro) throws ErrorServicio {
		if(libro == null) {
			throw new ErrorServicio("El libro no es válido");
		}
		if(libro.getEjemplaresRestantes()<=0) {
			throw new ErrorServicio("No quedean más libros para prestar");
		}
		
		libro.setEjemplaresPrestados(libro.getEjemplaresPrestados()+1);
		libro.setEjemplaresRestantes(libro.getEjemplaresRestantes()-1);
		
		guardarLibroObj(libro);
	}
	
	

	
}
