package com.proyecto.apartahotel.sispart.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.proyecto.apartahotel.sispart.entity.Nacionalidad;

public class RegionDTO {

	@NotNull
	private Nacionalidad nacionalidad;

	@NotEmpty
	private String nombre;

	public Nacionalidad getNacionalidad() {
		return nacionalidad;
	}

	public RegionDTO() {

	}

	public RegionDTO(@NotNull Nacionalidad nacionalidad, @NotEmpty String nombre) {

		this.nacionalidad = nacionalidad;
		this.nombre = nombre;
	}

	public void setNacionalidad(Nacionalidad nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
