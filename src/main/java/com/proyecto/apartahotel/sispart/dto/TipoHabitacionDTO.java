package com.proyecto.apartahotel.sispart.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class TipoHabitacionDTO {

	@NotEmpty
	private String nombre;

	@NotNull
	private Double  precioXPersona;
	
	@NotNull
	private Double precioXAcompanante;

	public TipoHabitacionDTO() {

	}

	public TipoHabitacionDTO(@NotEmpty String nombre, @NotNull Double  precioXPersona,@NotNull Double precioXAcompanante) {

		this.nombre = nombre;
		this.precioXPersona =  precioXPersona;
		this.precioXAcompanante = precioXAcompanante;
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

	public Double getPrecioXAcompanante() {
		return precioXAcompanante;
	}

	public void setPrecioXAcompanante(Double precioXAcompanante) {
		this.precioXAcompanante = precioXAcompanante;
	}
	
	


}
