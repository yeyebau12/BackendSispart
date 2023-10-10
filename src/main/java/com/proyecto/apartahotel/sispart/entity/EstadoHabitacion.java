package com.proyecto.apartahotel.sispart.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "estado_habitacion")
public class EstadoHabitacion implements Serializable {



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_estado_habitacion")
	private Long codEstadoHabitacion;

	@Column(length = 60, nullable = false)
	private String nombre;

	public EstadoHabitacion() {

	}

	public EstadoHabitacion(Long codEstadoHabitacion, String nombre) {

		this.codEstadoHabitacion = codEstadoHabitacion;
		this.nombre = nombre;
	}

	public EstadoHabitacion(String nombre) {

		this.nombre = nombre;
	}

	public Long getCodEstadoHabitacion() {
		return codEstadoHabitacion;
	}

	public void setCodEstadoHabitacion(Long codEstadoHabitacion) {
		this.codEstadoHabitacion = codEstadoHabitacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	private static final long serialVersionUID = -1683676964966193882L;
	
	

}
