package com.proyecto.apartahotel.sispart.huesped.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.proyecto.apartahotel.sispart.facturacion.entity.Factura;
import com.proyecto.apartahotel.sispart.nacionalidad.entity.Nacionalidad;
import com.proyecto.apartahotel.sispart.tipDocumento.entity.TipDocumento;

import ch.qos.logback.core.subst.Token.Type;

@Entity
@Table(name = "huespedes")
public class Huesped implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_Huesped")
	private Long codHuesped;

	@Column(length = 30, nullable = false)
	private String nombre;
	@Column(length = 30, nullable = false)
	private String apellido;
	@Column(length = 30, nullable = false)
	private String direccion;

	@Column(name = "num_celular")
	private Long numCelular;
	@Column(length = 50, nullable = false)
	private String correo;

	@ManyToOne
	@JoinColumn(name = "cod_tip_documento", nullable = false)
	private TipDocumento tipoDocumento;

	@Column(name = "num_documento", length = 30, nullable = false)
	private Long numDocumento;

	@ManyToOne
	@JoinColumn(name = "cod_nacionalidad", nullable = false)
	private Nacionalidad Nacionalidad;

	@Column(name = "lugar_origen", length = 30, nullable = false)
	private String lugarOrigen;

	@Column(name = "nom_contacto_emergencia", length = 30, nullable = false)
	private String nomContactoEmergencia;

	@Column(name = "num_contacto_emergencia", length = 30, nullable = false)
	private Long numContactoEmergencia;

	@Column(name = "estado_Huesped")
	private boolean estadoHuesped;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "huesped", cascade = CascadeType.ALL)
	@JsonIgnoreProperties(value = { "huesped", "hibernateLazyInitializer", "handler" }, allowSetters = true)
	private List<Factura> facturas;

	public Huesped() {

		this.facturas = new ArrayList<>();

	}

	public Huesped(Long codHuesped, String nombre, String apellido, String direccion, Long numCelular, String correo,
			TipDocumento tipoDocumento, Long numDocumento,
			com.proyecto.apartahotel.sispart.nacionalidad.entity.Nacionalidad nacionalidad, String lugarOrigen,
			String nomContactoEmergencia, Long numContactoEmergencia) {

		this.codHuesped = codHuesped;
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
		this.numCelular = numCelular;
		this.correo = correo;
		this.tipoDocumento = tipoDocumento;
		this.numDocumento = numDocumento;
		Nacionalidad = nacionalidad;
		this.lugarOrigen = lugarOrigen;
		this.nomContactoEmergencia = nomContactoEmergencia;
		this.numContactoEmergencia = numContactoEmergencia;

	}

	public Huesped(String nombre, String apellido, String direccion, Long numCelular, String correo,
			TipDocumento tipoDocumento, Long numDocumento,
			com.proyecto.apartahotel.sispart.nacionalidad.entity.Nacionalidad nacionalidad, String lugarOrigen,
			String nomContactoEmergencia, Long numContactoEmergencia) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
		this.numCelular = numCelular;
		this.correo = correo;
		this.tipoDocumento = tipoDocumento;
		this.numDocumento = numDocumento;
		Nacionalidad = nacionalidad;
		this.lugarOrigen = lugarOrigen;
		this.nomContactoEmergencia = nomContactoEmergencia;
		this.numContactoEmergencia = numContactoEmergencia;

	}

	public Long getCodHuesped() {
		return codHuesped;
	}

	public void setCodHuesped(Long codHuesped) {
		this.codHuesped = codHuesped;
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
		return Nacionalidad;
	}

	public void setNacionalidad(Nacionalidad nacionalidad) {
		Nacionalidad = nacionalidad;
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

	private static final long serialVersionUID = 2556030903210616284L;

}
