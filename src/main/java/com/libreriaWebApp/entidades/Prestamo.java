package com.libreriaWebApp.entidades;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Prestamo {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	private Date fechaPrestamo;
	private Date fechaDevolucion;
	private Boolean alta;
	@OneToOne
	private Libro libro;
	@OneToOne
	private Cliente cliente;

	public Prestamo() {
		this.alta = true;
	}

	public Prestamo( Date fechaPrestamo, Date fechaDevolucion, Libro libro, Cliente cliente) {
		
		this.fechaPrestamo = fechaPrestamo;
		this.fechaDevolucion = fechaDevolucion;
		this.alta = true;
		this.libro = libro;
		this.cliente = cliente;
	}

	public String getId() {
		return id;
	}

	public Date getFechaPrestamo() {
		return fechaPrestamo;
	}

	public Date getFechaDevolucion() {
		return fechaDevolucion;
	}

	public Boolean getAlta() {
		return alta;
	}

	public Libro getLibro() {
		return libro;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setFechaPrestamo(Date fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}

	public void setFechaDevolucion(Date fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}

	public void setAlta(Boolean alta) {
		this.alta = alta;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "Prestamo [id=" + id + ", fechaPrestamo=" + fechaPrestamo + ", fechaDevolucion=" + fechaDevolucion
				+ ", alta=" + alta + ", libro=" + libro + ", cliente=" + cliente + "]";
	}

}
