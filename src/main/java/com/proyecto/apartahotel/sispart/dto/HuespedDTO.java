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
	private Nacionalidad nacionalidad;
	@NotEmpty
	private String lugarOrigen;

	private String nomContactoEmergencia;
	private Long numContactoEmergencia;

	private boolean estadoHuesped = true;
	private List<Factura> facturas;

	public HuespedDTO() {

		this.facturas = new ArrayList<>();

	}

	public HuespedDTO(@NotEmpty Date fechaEntrada, @NotEmpty Date fechaSalida, @NotEmpty String nombre,
			@NotEmpty String apellido, @NotNull Long numCelular, @Email @NotEmpty String correo,
			@NotNull TipDocumento tipoDocumento, @NotNull Long numDocumento, @NotNull Nacionalidad nacionalidad,
			@NotEmpty String lugarOrigen, String nomContactoEmergencia, Long numContactoEmergencia,
			Habitacion codHabitacion) {

		this.nombre = nombre;
		this.apellido = apellido;
		this.numCelular = numCelular;
		this.correo = correo;
		this.tipoDocumento = tipoDocumento;
		this.numDocumento = numDocumento;
		this.nacionalidad = nacionalidad;
		this.lugarOrigen = lugarOrigen;
		this.nomContactoEmergencia = nomContactoEmergencia;
		this.numContactoEmergencia = numContactoEmergencia;

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

	public Nacionalidad getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(Nacionalidad nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getLugarOrigen() {
		return lugarOrigen;
	}

	public void setLugarOrigen(String lugarOrigen) {
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

	public List<Factura> getFacturas() {
		return facturas;
	}

	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}

}
