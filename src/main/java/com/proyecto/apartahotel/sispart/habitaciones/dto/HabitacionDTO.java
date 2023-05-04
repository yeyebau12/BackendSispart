package com.proyecto.apartahotel.sispart.habitaciones.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class HabitacionDTO {

	@NotEmpty
	private String nombreHabitacion;
	@NotEmpty
	private String descripHabitacion;
	@NotNull
	private Integer numHabitacion;
	@NotNull
	private Integer pisoHabitacion;
	@NotNull
	private Integer maxPersonasDisponibles;
	@NotNull
	private Integer precioHabitacion;

	private String estadoHabitacion;
	private String imagenHabitacion;

	public HabitacionDTO() {

	}

	public HabitacionDTO(@NotEmpty String nombreHabitacion, @NotEmpty String descripHabitacion,
			@NotNull Integer numHabitacion, @NotNull Integer pisoHabitacion, @NotNull Integer maxPersonasDisponibles,
			@NotNull Integer precioHabitacion, String estadoHabitacion, String imagenHabitacion) {

		this.nombreHabitacion = nombreHabitacion;
		this.descripHabitacion = descripHabitacion;
		this.numHabitacion = numHabitacion;
		this.pisoHabitacion = pisoHabitacion;
		this.maxPersonasDisponibles = maxPersonasDisponibles;
		this.precioHabitacion = precioHabitacion;
		this.estadoHabitacion = estadoHabitacion;
		this.imagenHabitacion = imagenHabitacion;
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

}
