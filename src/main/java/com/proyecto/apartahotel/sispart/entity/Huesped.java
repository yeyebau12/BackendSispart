package com.proyecto.apartahotel.sispart.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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

	@Column(name = "num_celular")
	private Long numCelular;

	@Column(length = 50, nullable = false)
	private String correo;

	@ManyToOne
	@JoinColumn(name = "cod_tip_documento", nullable = false)
	private TipDocumento tipoDocumento;

	@Column(name = "num_documento", length = 30, nullable = false, unique = true)
	private Long numDocumento;

	@Column(name = "fecha_nacimiento", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT-5")
	private Date fechaNacimiento;

	@Column(nullable = false)
	private Integer edad;

	@ManyToOne
	@JoinColumn(name = "cod_nacionalidad" ,nullable = false)
	private Nacionalidad nacionalidad;

	@ManyToOne
	@JoinColumn(name = "cod_region", nullable = false)
	private Region lugarOrigen;

	@Column(name = "nom_contacto_emergencia", length = 30)
	private String nomContactoEmergencia;

	@Column(name = "num_contacto_emergencia", length = 30)
	private Long numContactoEmergencia;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "codHuesped", cascade = CascadeType.ALL)
	@JsonIgnoreProperties(value = { "codHuesped", "hibernateLazyInitializer", "handler" }, allowSetters = true)
	private List<CheckIn> checkin;

	@Column(name = "estado_Huesped")
	private boolean estadoHuesped = true;



	public Huesped() {

		this.checkin = new ArrayList<>();

	}

	public Huesped(Long codHuesped, String nombre, String apellido, Long numCelular, String correo,
			TipDocumento tipoDocumento, Long numDocumento, Date fechaNacimiento, Integer edad,
			Nacionalidad nacionalidad, Region lugarOrigen, String nomContactoEmergencia, Long numContactoEmergencia) {

		this.codHuesped = codHuesped;
		this.nombre = nombre;
		this.apellido = apellido;
		this.numCelular = numCelular;
		this.correo = correo;
		this.tipoDocumento = tipoDocumento;
		this.numDocumento = numDocumento;
		this.fechaNacimiento = fechaNacimiento;
		this.edad = edad;
		this.nacionalidad = nacionalidad;
		this.lugarOrigen = lugarOrigen;
		this.nomContactoEmergencia = nomContactoEmergencia;
		this.numContactoEmergencia = numContactoEmergencia;

	}

	public Huesped(String nombre, String apellido, Long numCelular, String correo, TipDocumento tipoDocumento,
			Long numDocumento, Date fechaNacimiento, Integer edad, Nacionalidad nacionalidad, Region lugarOrigen,
			String nomContactoEmergencia, Long numContactoEmergencia) {

		this.nombre = nombre;
		this.apellido = apellido;
		this.numCelular = numCelular;
		this.correo = correo;
		this.tipoDocumento = tipoDocumento;
		this.numDocumento = numDocumento;
		this.fechaNacimiento = fechaNacimiento;
		this.edad = edad;
		this.nacionalidad = nacionalidad;
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

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
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

	public List<CheckIn> getCheckin() {
		return checkin;
	}

	public void setCheckin(List<CheckIn> checkin) {
		this.checkin = checkin;
	}

	private static final long serialVersionUID = 2556030903210616284L;

}
