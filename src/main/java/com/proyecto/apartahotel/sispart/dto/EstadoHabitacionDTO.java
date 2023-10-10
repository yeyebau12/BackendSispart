package com.proyecto.apartahotel.sispart.dto;

import javax.validation.constraints.NotEmpty;

public class EstadoHabitacionDTO {

	@NotEmpty
	private String nombre;

	public EstadoHabitacionDTO() {

	}

	public EstadoHabitacionDTO(@NotEmpty String nombre) {

		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
