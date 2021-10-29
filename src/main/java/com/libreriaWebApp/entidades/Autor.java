package com.libreriaWebApp.entidades;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Autor {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	private String nombre;
	private String apellido;
	private Boolean alta;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "autor", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Libro> librosEscritos;

	public Autor() {
		this.alta = true;
	}

	public Autor( String nombre, String apellido, List<Libro> librosEscritos) {
		
		this.nombre = nombre;
		this.apellido = apellido;
		this.alta = true;
		this.librosEscritos = librosEscritos;
	}

	public String getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public Boolean getAlta() {
		return alta;
	}

	public List<Libro> getLibrosEscritos() {
		return librosEscritos;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setAlta(Boolean alta) {
		this.alta = alta;
	}

	public void setLibrosEscritos(List<Libro> librosEscritos) {
		this.librosEscritos = librosEscritos;
	}
	

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	@Override
	public String toString() {
		return "Autor [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", alta=" + alta
				+ ", librosEscritos=" + librosEscritos + "]";
	}

}
