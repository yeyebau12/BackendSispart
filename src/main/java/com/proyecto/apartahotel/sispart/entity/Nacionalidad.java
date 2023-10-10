package com.proyecto.apartahotel.sispart.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "nacionalidad")
public class Nacionalidad implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_nacion")
	private int codNacion;

	@Column(length = 60, nullable = false)
	private String nombre;

	public Nacionalidad() {

	}

	public Nacionalidad(int codNacion, String nombre) {

		this.codNacion = codNacion;
		this.nombre = nombre;
	}

	public Nacionalidad(String nombre) {
		
		this.nombre = nombre;
	}

	public int getCodNacion() {
		return codNacion;
	}

	public void setCodNacion(int codNacion) {
		this.codNacion = codNacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	private static final long serialVersionUID = 512450089846902810L;

}
