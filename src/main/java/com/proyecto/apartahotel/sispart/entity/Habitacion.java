package com.proyecto.apartahotel.sispart.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "habitacion")
public class Habitacion implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codHabitacion;

	@Column(length = 50, nullable = false)
	private String nombreHabitacion;
	@Column(nullable = false)
	private String descripHabitacion;
	@Column(name = "num_habitacion", nullable = false)
	private Integer numHabitacion;
	@Column(name = "piso_habitacion", nullable = false)
	private Integer pisoHabitacion;
	@Column(name = "max_personas_habitacion", nullable = false)
	private Integer maxPersonasDisponibles;
	@Column(name = "precio_habitacion", nullable = false)
	private Integer precioHabitacion;
	@Column(name = "estado_habitacion", nullable = false)
	private String estadoHabitacion;
	private String imagenHabitacion;

	public Habitacion() {

	}

	public Habitacion(Long codHabitacion, String nombreHabitacion, String descripHabitacion, Integer numHabitacion,
			Integer pisoHabitacion, Integer maxPersonasDisponibles, Integer precioHabitacion, String estadoHabitacion) {

		this.codHabitacion = codHabitacion;
		this.nombreHabitacion = nombreHabitacion;
		this.descripHabitacion = descripHabitacion;
		this.numHabitacion = numHabitacion;
		this.pisoHabitacion = pisoHabitacion;
		this.maxPersonasDisponibles = maxPersonasDisponibles;
		this.precioHabitacion = precioHabitacion;
		this.estadoHabitacion = estadoHabitacion;

	}

	public Habitacion(String nombreHabitacion, String descripHabitacion, Integer numHabitacion, Integer pisoHabitacion,
			Integer maxPersonasDisponibles, Integer precioHabitacion, String estadoHabitacion) {

		this.nombreHabitacion = nombreHabitacion;
		this.descripHabitacion = descripHabitacion;
		this.numHabitacion = numHabitacion;
		this.pisoHabitacion = pisoHabitacion;
		this.maxPersonasDisponibles = maxPersonasDisponibles;
		this.precioHabitacion = precioHabitacion;
		this.estadoHabitacion = estadoHabitacion;

	}

	public Long getCodHabitacion() {
		return codHabitacion;
	}

	public void setCodHabitacion(Long codHabitacion) {
		this.codHabitacion = codHabitacion;
	}

	public String getNombreHabitacion() {
		return nombreHabitacion;
	}

	public void setNombreHabitacion(String nombreHabitacion) {
		this.nombreHabitacion = nombreHabitacion;
	}

	public String getDescripHabitacion() {
		return descripHabitacion;
	}

	public void setDescripHabitacion(String descripHabitacion) {
		this.descripHabitacion = descripHabitacion;
	}

	public Integer getNumHabitacion() {
		return numHabitacion;
	}

	public void setNumHabitacion(Integer numHabitacion) {
		this.numHabitacion = numHabitacion;
	}

	public Integer getPisoHabitacion() {
		return pisoHabitacion;
	}

	public void setPisoHabitacion(Integer pisoHabitacion) {
		this.pisoHabitacion = pisoHabitacion;
	}

	public Integer getMaxPersonasDisponibles() {
		return maxPersonasDisponibles;
	}

	public void setMaxPersonasDisponibles(Integer maxPersonasDisponibles) {
		this.maxPersonasDisponibles = maxPersonasDisponibles;
	}

	public Integer getPrecioHabitacion() {
		return precioHabitacion;
	}

	public void setPrecioHabitacion(Integer precioHabitacion) {
		this.precioHabitacion = precioHabitacion;
	}

	public String getEstadoHabitacion() {
		return estadoHabitacion;
	}

	public void setEstadoHabitacion(String estadoHabitacion) {
		this.estadoHabitacion = estadoHabitacion;
	}

	public String getImagenHabitacion() {
		return imagenHabitacion;
	}

	public void setImagenHabitacion(String imagenHabitacion) {
		this.imagenHabitacion = imagenHabitacion;
	}

	private static final long serialVersionUID = 6472275847130544633L;

}
