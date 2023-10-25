package com.proyecto.apartahotel.sispart.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.proyecto.apartahotel.sispart.entity.Empleado;
import com.proyecto.apartahotel.sispart.entity.Role;
import com.proyecto.apartahotel.sispart.entity.TipDocumento;

public class UsuarioEmpleadoDTO {

	private Empleado empleado;

	@NotEmpty
	private String userName;

	@NotEmpty
	@Size(min = 4, max = 10)
	private String contrasena;

	@NotEmpty
	@Size(min = 4, max = 10)
	private String confirContrasena;

	private Boolean enabled = true;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "GMT-5")
	private Date createUser;

	private List<Role> rol;

	public UsuarioEmpleadoDTO() {

	}

	public UsuarioEmpleadoDTO(Empleado empleado, @NotEmpty String userName,
			@NotEmpty @Size(min = 4, max = 10) String contrasena,
			@NotEmpty @Size(min = 4, max = 10) String confirContrasena, List<Role> rol) {
		this.empleado = empleado;
		this.userName = userName;
		this.contrasena = contrasena;
		this.confirContrasena = confirContrasena;
		this.rol = rol;
	}
	

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getConfirContrasena() {
		return confirContrasena;
	}

	public void setConfirContrasena(String confircontrasena) {
		this.confirContrasena = confircontrasena;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Date getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Date createUser) {
		this.createUser = createUser;
	}

	public List<Role> getRol() {
		return rol;
	}

	public void setRol(List<Role> rol) {
		this.rol = rol;
	}

}
