package com.proyecto.apartahotel.sispart.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipo_habitacion")
public class TipoHabitacion implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_tipo_habitacion")
	private Long codTipoHabitacion;

	@Column(length = 60, nullable = false)
	private String nombre;

	@Column(name = "precio_persona", nullable = false)
	private Double precioXPersona;

	public TipoHabitacion() {

	}

	public TipoHabitacion(Long codTipoHabitacion, String nombre, double precioXPersona) {

		this.codTipoHabitacion = codTipoHabitacion;
		this.nombre = nombre;
		this.precioXPersona = precioXPersona;
	}

	public TipoHabitacion(String nombre, double precioXPersona) {

		this.nombre = nombre;
		this.precioXPersona = precioXPersona;
	}

	public Long getCodTipoHabitacion() {
		return codTipoHabitacion;
	}

	public void setCodTipoHabitacion(Long codTipoHabitacion) {
		this.codTipoHabitacion = codTipoHabitacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getPrecioXPersona() {
		return precioXPersona;
	}

	public void setPrecioXPersona(Double precioXPersona) {
		this.precioXPersona = precioXPersona;
	}

	private static final long serialVersionUID = -4032596186669313168L;

}
