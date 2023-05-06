package com.proyecto.apartahotel.sispart.huesped.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.proyecto.apartahotel.sispart.facturacion.entity.Factura;
import com.proyecto.apartahotel.sispart.nacionalidad.entity.Nacionalidad;
import com.proyecto.apartahotel.sispart.reservacion.entity.Reservacion;
import com.proyecto.apartahotel.sispart.tipDocumento.entity.TipDocumento;

public class HuespedDTO {

	@NotEmpty
	private String nombre;
	@NotEmpty
	private String apellido;
	@NotEmpty
	private String direccion;
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
	private boolean estadoHuesped;

	private List<Factura> facturas;

	private List<Reservacion> reservacion;

	public HuespedDTO() {

		this.facturas = new ArrayList<>();
		this.reservacion = new ArrayList<>();

	}

	public HuespedDTO(@NotEmpty String nombre, @NotEmpty String apellido, @NotEmpty String direccion,
			@NotNull Long numCelular, @Email @NotEmpty String correo, @NotNull TipDocumento tipoDocumento,
			@NotNull Long numDocumento, @NotNull Nacionalidad nacionalidad, @NotEmpty String lugarOrigen,
			String nomContactoEmergencia, Long numContactoEmergencia, List<Reservacion> reservacion) {

		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
		this.numCelular = numCelular;
		this.correo = correo;
		this.tipoDocumento = tipoDocumento;
		this.numDocumento = numDocumento;
		this.nacionalidad = nacionalidad;
		this.lugarOrigen = lugarOrigen;
		this.nomContactoEmergencia = nomContactoEmergencia;
		this.numContactoEmergencia = numContactoEmergencia;
		this.reservacion = reservacion;
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

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
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

	public List<Reservacion> getReservacion() {
		return reservacion;
	}

	public void setReservacion(List<Reservacion> reservacion) {
		this.reservacion = reservacion;
	}

}
