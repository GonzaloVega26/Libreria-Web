package com.libreriaWebApp.servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.libreriaWebApp.entidades.Prestamo;
import com.libreriaWebApp.errores.ErrorServicio;
import com.libreriaWebApp.repositorios.PrestamoRepositorio;

@Service
public class PrestamoServicio {
	@Autowired
	private PrestamoRepositorio prestamoRp;
	@Autowired
	private LibroServicio libroSv;

	@Transactional
	public void guardarPrestamo(Prestamo prestamo) throws ErrorServicio {

		validate(prestamo);
		libroSv.prestarLibro(prestamo.getLibro());
		prestamoRp.save(prestamo);
	}

	@Transactional(readOnly = true)
	public Prestamo buscarPrestamoPorID(String id) throws ErrorServicio {
		Optional<Prestamo> respuesta = prestamoRp.findById(id);
		if (respuesta.isPresent()) {
			return respuesta.get();
		} else {
			throw new ErrorServicio("El prestamo no existe");
		}

	}

	private void validate(Prestamo prestamo) throws ErrorServicio {
		if (prestamo.getUsuario() == null) {
			throw new ErrorServicio("El usuario no es válido");
		}
		if (prestamo.getLibro() == null) {
			throw new ErrorServicio("El libro no es válido");

		}

		if (prestamo.getLibro().getEjemplaresRestantes() <= 0) {
			throw new ErrorServicio("No hay ejemplares para prestar");
		}

	}
}
