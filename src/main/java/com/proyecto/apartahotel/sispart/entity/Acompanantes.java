package com.proyecto.apartahotel.sispart.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "acompañantes")
public class Acompanantes implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_acompañante")
	private Long codAcompanante;

	@Column(length = 30, nullable = false)
	private String nombre;

	@Column(length = 30, nullable = false)
	private String apellido;

	@ManyToOne
	@JoinColumn(name = "cod_tip_documento", nullable = false)
	private TipDocumento tipoDocumento;

	@Column(name = "num_documento", length = 30, nullable = false)
	private Long numDocumento;

	@Column(name = "fecha_nacimiento", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT-5")
	private Date fechaNacimiento;
	

	@Column(nullable = false)
	private Integer edad;
	

	public Acompanantes() {

	}

	
	

	public Acompanantes(Long codAcompanante, String nombre, String apellido, TipDocumento tipoDocumento,
			Long numDocumento, Date fechaNacimiento,Integer edad) {
		
		this.codAcompanante = codAcompanante;
		this.nombre = nombre;
		this.apellido = apellido;
		this.tipoDocumento = tipoDocumento;
		this.numDocumento = numDocumento;
		this.fechaNacimiento = fechaNacimiento;
		this.edad = edad;

	}

	
	
	public Acompanantes(String nombre, String apellido, TipDocumento tipoDocumento, Long numDocumento,
			Date fechaNacimiento,  Integer edad) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.tipoDocumento = tipoDocumento;
		this.numDocumento = numDocumento;
		this.fechaNacimiento = fechaNacimiento;
		this.edad = edad;
		
	}




	public Long getCodAcompanante() {
		return codAcompanante;
	}

	public void setCodAcompanante(Long codAcompanante) {
		this.codAcompanante = codAcompanante;
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










	private static final long serialVersionUID = -1486076546707234386L;

}
