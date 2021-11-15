package com.libreriaWebApp.entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.libreriaWebApp.enums.Role;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	private Long dni;
	private String nombre;
	private String mail;
	private String apellido;
	private String telefono;
	private Boolean alta;
	@ElementCollection(targetClass=Role.class)
    @Enumerated(EnumType.STRING) // Possibly optional (I'm not sure) but defaults to ORDINAL.
    @CollectionTable(name="user_role")
    @Column(name="role") // Column name in person_interest
	private List<Role> roles = new ArrayList<Role>();
	private String clave;
	public Usuario() {
		this.alta = true;
		generaUser();
	}

	public Usuario(Long dni, String nombre, String apellido, String telefono, String mail) {

		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.alta = true;
		this.mail = mail;
		generaUser();
	}

	private void generaUser(){
		this.roles.add(Role.ROLE_USER);
		
	}

	public String getId() {
		return id;
	}

	public Long getDni() {
		return dni;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public String getTelefono() {
		return telefono;
	}

	public Boolean getAlta() {
		return alta;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setDni(Long dni) {
		this.dni = dni;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public void setAlta(Boolean alta) {
		this.alta = alta;
	}
	
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getClave() {
		return clave;
	}
	public List<Role> getRoles() {
		return roles;
	}

	

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + ", telefono="
				+ telefono + ", alta=" + alta + "]";
	}

}
