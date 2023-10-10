package com.proyecto.apartahotel.sispart.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TipoHabitacionDTO {

	@NotEmpty
	private String nombre;

	@NotNull
	private Double preciDia;

	public TipoHabitacionDTO() {

	}

	public TipoHabitacionDTO(@NotEmpty String nombre, @NotNull Double preciDia) {

		this.nombre = nombre;
		this.preciDia = preciDia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getPreciDia() {
		return preciDia;
	}

	public void setPreciDia(Double preciDia) {
		this.preciDia = preciDia;
	}

}
