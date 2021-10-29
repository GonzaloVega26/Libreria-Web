package com.libreriaWebApp.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.libreriaWebApp.entidades.Autor;
@Repository
public interface AutorRepositorio extends JpaRepository<Autor, String> {
	
	@Query("SELECT c FROM Autor c WHERE c.id = :id")
	public Autor buscarAutorPorId(@Param("id") String id);
	
	@Query("SELECT c FROM Autor c WHERE c.nombre = :nombre AND c.apellido =:apellido")
	public Autor buscarAutorPorNombreApellido(@Param("nombre") String nombre,
									          @Param("apellido") String apellido);
	
}
