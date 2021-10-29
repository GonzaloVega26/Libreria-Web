package com.libreriaWebApp.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.libreriaWebApp.entidades.Editorial;

@Repository
public interface EditorialRepositorio extends JpaRepository<Editorial, String> {
	
	@Query("SELECT c FROM Editorial c WHERE c.id = :id")
	public Editorial buscarEditorialPorId(@Param("id") String id);
	
	@Query("SELECT c FROM Editorial c WHERE c.nombre = :nombre")
	public Editorial buscarEditorialPorNombre(@Param("nombre") String nombre);
}
