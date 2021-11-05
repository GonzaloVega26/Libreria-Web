package com.libreriaWebApp.entidades;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Libro {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	private String isbn;
	private String nombre;
	private Integer anio;
	private Integer ejemplares;
	private Integer ejemplaresPrestados;
	private Integer ejemplaresRestantes;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "autorId")
	private Autor autor;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "editorialId")
	private Editorial editorial;
	private Boolean alta;

	public Libro() {
		this.alta = true;
	}

	public Libro(String isbn, String nombre, Integer anio, Integer ejemplares, Integer ejemplaresPrestados,
			Integer ejemplaresRestantes, Autor autor, Editorial editorial) {
		
		this.isbn = isbn;
		this.nombre = nombre;
		this.anio = anio;
		this.ejemplares = ejemplares;
		this.ejemplaresPrestados = ejemplaresPrestados;
		this.ejemplaresRestantes = ejemplaresRestantes;
		this.autor = autor;
		this.editorial = editorial;
		this.alta = true;
	}

	public String getId() {
		return id;
	}

	public String getIsbn() {
		return isbn;
	}

	public String getNombre() {
		return nombre;
	}

	public Integer getAnio() {
		return anio;
	}

	public Integer getEjemplares() {
		return ejemplares;
	}

	public Integer getEjemplaresPrestados() {
		return ejemplaresPrestados;
	}

	public Integer getEjemplaresRestantes() {
		return ejemplaresRestantes;
	}

	public Autor getAutor() {
		return autor;
	}

	public Editorial getEditorial() {
		return editorial;
	}

	public Boolean getAlta() {
		return alta;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public void setEjemplares(Integer ejemplares) {
		this.ejemplares = ejemplares;
	}

	public void setEjemplaresPrestados(Integer ejemplaresPrestados) {
		this.ejemplaresPrestados = ejemplaresPrestados;
	}

	public void setEjemplaresRestantes(Integer ejemplaresRestantes) {
		this.ejemplaresRestantes = ejemplaresRestantes;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public void setEditorial(Editorial editorial) {
		this.editorial = editorial;
	}

	public void setAlta(Boolean alta) {
		this.alta = alta;
	}

	@Override
	public String toString() {
		return "Libro [id=" + id + ", isbn=" + isbn + ", nombre=" + nombre + ", anio=" + anio + ", ejemplares="
				+ ejemplares + ", ejemplaresPrestados=" + ejemplaresPrestados + ", ejemplaresRestantes="
				+ ejemplaresRestantes + ", autor=" + autor.getNombre() + " " + autor.getApellido() + ", editorial=" + editorial + ", alta=" + alta + "]";
	}

}
