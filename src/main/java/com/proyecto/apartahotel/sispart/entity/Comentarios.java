package com.proyecto.apartahotel.sispart.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "comentarios")
public class Comentarios {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_comentario")
	private Long codComentario;
	@Column(name = "nombre", length = 30, nullable = false)
	private String nombre;
	@Column(name = "email", length = 50, nullable = false)
	private String email;
	@Column(name = "numTelefono", length = 30, nullable = false)
	private Long numTelefono;
	@Column(name = "comentario", nullable = false)
	private String comentario;
	@Column(name = "fecha_enviado", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "GMT-5")
	private Date fechaEnviado;
	@Column(name = "hora_enviado", nullable = false)
	@Temporal(TemporalType.TIME)
	@DateTimeFormat(pattern = "HH:mm:ss")
	@JsonFormat(pattern = "HH:mm:ss", timezone = "GMT-5")
	private Date horaEnviado;

	public Comentarios() {

	}

	public Comentarios(Long codComentario, String nombre, String email, Long numTelefono, String comentario) {
		this.codComentario = codComentario;
		this.nombre = nombre;
		this.email = email;
		this.numTelefono = numTelefono;
		this.comentario = comentario;
	}

	public Comentarios(String nombre, String email, Long numTelefono, String comentario) {

		this.nombre = nombre;
		this.email = email;
		this.numTelefono = numTelefono;
		this.comentario = comentario;
	}

	@PrePersist
	public void prePersist() {
		fechaEnviado = new Date();
		horaEnviado = new Date();

	}

	public Long getCodComentario() {
		return codComentario;
	}

	public void setCodComentario(Long codComentario) {
		this.codComentario = codComentario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getNumTelefono() {
		return numTelefono;
	}

	public void setNumTelefono(Long numTelefono) {
		this.numTelefono = numTelefono;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Date getFechaEnviado() {
		return fechaEnviado;
	}

	public void setFechaEnviado(Date fechaEnviado) {
		this.fechaEnviado = fechaEnviado;
	}

	public Date getHoraEnviado() {
		return horaEnviado;
	}

	public void setHoraEnviado(Date horaEnviado) {
		this.horaEnviado = horaEnviado;
	}

}
