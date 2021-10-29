package com.libreriaWebApp.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.libreriaWebApp.entidades.Libro;

@Repository
public interface LibroRepositorio extends JpaRepository<Libro, String> {
	@Query("SELECT c FROM Libro c WHERE c.id = :id")
	public Libro buscarLibroPorId(@Param("id") String id);

}
