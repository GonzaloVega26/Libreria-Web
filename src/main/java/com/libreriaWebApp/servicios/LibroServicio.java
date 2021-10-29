package com.libreriaWebApp.servicios;

import java.time.LocalDate;
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

	
}
