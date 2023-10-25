package com.proyecto.apartahotel.sispart.dto;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.proyecto.apartahotel.sispart.entity.EstadoHabitacion;

import com.proyecto.apartahotel.sispart.entity.TipoHabitacion;

public class HabitacionDTO {

	@NotNull
	private TipoHabitacion nombreHabitacion;
	@NotEmpty
	private String descripHabitacion;
	@NotNull
	private Integer numHabitacion;
	@NotNull
	private Integer pisoHabitacion;
	@NotNull
	private Integer maxPersonasDisponibles;
    @NotNull
	private EstadoHabitacion estadoHabitacion;
    
	private String imagenHabitacion;

	public HabitacionDTO() {

	}

	public HabitacionDTO(@NotNull TipoHabitacion nombreHabitacion, @NotEmpty String descripHabitacion,
			@NotNull Integer numHabitacion, @NotNull Integer pisoHabitacion, @NotNull Integer maxPersonasDisponibles,
			EstadoHabitacion estadoHabitacion, String imagenHabitacion) {

		this.nombreHabitacion = nombreHabitacion;
		this.descripHabitacion = descripHabitacion;
		this.numHabitacion = numHabitacion;
		this.pisoHabitacion = pisoHabitacion;
		this.maxPersonasDisponibles = maxPersonasDisponibles;
		this.estadoHabitacion = estadoHabitacion;
		this.imagenHabitacion = imagenHabitacion;
	}

	public TipoHabitacion getNombreHabitacion() {
		return nombreHabitacion;
	}

	public void setNombreHabitacion(TipoHabitacion nombreHabitacion) {
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

	public EstadoHabitacion getEstadoHabitacion() {
		return estadoHabitacion;
	}

	public void setEstadoHabitacion(EstadoHabitacion estadoHabitacion) {
		this.estadoHabitacion = estadoHabitacion;
	}

	public String getImagenHabitacion() {
		return imagenHabitacion;
	}

	public void setImagenHabitacion(String imagenHabitacion) {
		this.imagenHabitacion = imagenHabitacion;
	}

	/*
	 * public Factura getFacturas() { return facturas; }
	 * 
	 * public void setFacturas(Factura facturas) { this.facturas = facturas; }
	 */

}
