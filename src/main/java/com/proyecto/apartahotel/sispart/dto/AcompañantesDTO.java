package com.proyecto.apartahotel.sispart.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.proyecto.apartahotel.sispart.entity.CheckIn;
import com.proyecto.apartahotel.sispart.entity.Huesped;
import com.proyecto.apartahotel.sispart.entity.TipDocumento;

public class AcompañantesDTO {

	@NotEmpty
	private String nombre;

	@NotEmpty
	private String apellido;

	@NotNull
	private TipDocumento tipoDocumento;

	@NotNull
	private Long numDocumento;

	@NotNull
	private Date fechaNacimiento;


	private Integer edad;


	public AcompañantesDTO() {

	}



	public AcompañantesDTO(@NotEmpty String nombre, @NotEmpty String apellido, @NotNull TipDocumento tipoDocumento,
			@NotNull Long numDocumento, @NotNull Date fechaNacimiento, @NotNull CheckIn codCheckin,Integer edad) {
		
		this.nombre = nombre;
		this.apellido = apellido;
		this.tipoDocumento = tipoDocumento;
		this.numDocumento = numDocumento;
		this.fechaNacimiento = fechaNacimiento;
		this.edad = edad;
	}



	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public TipDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public Long getNumDocumento() {
		return numDocumento;
	}

	public void setNumDocumento(Long numDocumento) {
		this.numDocumento = numDocumento;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}





}
