package com.proyecto.apartahotel.sispart.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.proyecto.apartahotel.sispart.entity.Factura;
import com.proyecto.apartahotel.sispart.entity.Habitacion;
import com.proyecto.apartahotel.sispart.entity.Nacionalidad;
import com.proyecto.apartahotel.sispart.entity.Region;
import com.proyecto.apartahotel.sispart.entity.TipDocumento;

public class HuespedDTO {

	@NotEmpty
	private String nombre;

	@NotEmpty
	private String apellido;
	@NotNull
	private Long numCelular;
	@Email
	@NotEmpty
	private String correo;

	@NotNull
	private TipDocumento tipoDocumento;

	@NotNull
	private Long numDocumento;

	@NotNull
	private Date fechaNacimiento;

	@NotNull
	private Nacionalidad nacionalidad;
	@NotEmpty
	private Region lugarOrigen;

	private String nomContactoEmergencia;
	private Long numContactoEmergencia;

	private boolean estadoHuesped = true;
	

	public HuespedDTO() {

		

	}

	public HuespedDTO(@NotEmpty String nombre, @NotEmpty String apellido, @NotNull Long numCelular,
			@Email @NotEmpty String correo, @NotNull TipDocumento tipoDocumento, @NotNull Long numDocumento,
			@NotNull Date fechaNacimiento, @NotNull Nacionalidad nacionalidad, @NotEmpty Region lugarOrigen,
			String nomContactoEmergencia, Long numContactoEmergencia) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.numCelular = numCelular;
		this.correo = correo;
		this.tipoDocumento = tipoDocumento;
		this.numDocumento = numDocumento;
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
		this.lugarOrigen = lugarOrigen;
		this.nomContactoEmergencia = nomContactoEmergencia;
		this.numContactoEmergencia = numContactoEmergencia;
		this.estadoHuesped = estadoHuesped;
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

	public Long getNumCelular() {
		return numCelular;
	}

	public void setNumCelular(Long numCelular) {
		this.numCelular = numCelular;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
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

	public Nacionalidad getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(Nacionalidad nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public Region getLugarOrigen() {
		return lugarOrigen;
	}

	public void setLugarOrigen(Region lugarOrigen) {
		this.lugarOrigen = lugarOrigen;
	}

	public String getNomContactoEmergencia() {
		return nomContactoEmergencia;
	}

	public void setNomContactoEmergencia(String nomContactoEmergencia) {
		this.nomContactoEmergencia = nomContactoEmergencia;
	}

	public Long getNumContactoEmergencia() {
		return numContactoEmergencia;
	}

	public void setNumContactoEmergencia(Long numContactoEmergencia) {
		this.numContactoEmergencia = numContactoEmergencia;
	}

	public boolean isEstadoHuesped() {
		return estadoHuesped;
	}

	public void setEstadoHuesped(boolean estadoHuesped) {
		this.estadoHuesped = estadoHuesped;
	}

}
