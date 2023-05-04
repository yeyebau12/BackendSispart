package com.proyecto.apartahotel.sispart.comentarios.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ComentariosDTO {

	@NotEmpty
	private String nombre;
	@NotEmpty
	private String email;
	@NotNull
	private Long numTelefono;
	@NotEmpty
	private String comentario;

	private Date fechaEnviado;
	private Date horaEnviado;

	public ComentariosDTO() {

	}

	public ComentariosDTO(@NotEmpty String nombre, @NotEmpty String email, @NotNull Long numTelefono,
			@NotEmpty String comentario) {

		this.nombre = nombre;
		this.email = email;
		this.numTelefono = numTelefono;
		this.comentario = comentario;
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
