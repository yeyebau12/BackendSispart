package com.proyecto.apartahotel.sispart.nacionalidad.dto;

import javax.validation.constraints.NotEmpty;

public class NacionalidaDTO {

	@NotEmpty
	private String nombre;

	public NacionalidaDTO() {

	}

	public NacionalidaDTO(@NotEmpty String nombre) {

		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
