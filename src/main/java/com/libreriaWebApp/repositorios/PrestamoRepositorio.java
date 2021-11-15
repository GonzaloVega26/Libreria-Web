package com.libreriaWebApp.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libreriaWebApp.entidades.Prestamo;

@Repository
public interface PrestamoRepositorio extends JpaRepository<Prestamo, String> {

}
