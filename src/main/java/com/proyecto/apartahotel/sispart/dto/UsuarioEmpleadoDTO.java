package com.proyecto.apartahotel.sispart.dto;

import java.util.Date;
import java.util.List;

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

	@NotNull
	private TipDocumento tipDocumento;

	@NotNull
	private Long numDocumento;

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

	public UsuarioEmpleadoDTO(@NotEmpty TipDocumento tipDocumento, @NotEmpty Long numDocumento,
			@NotEmpty String userName, @NotEmpty @Size(min = 4, max = 10) String contrasena,
			@NotEmpty @Size(min = 4, max = 10) String confirContrasena, Boolean enabled,
			List<Role> rol) {
		this.tipDocumento = tipDocumento;
		this.numDocumento = numDocumento;
		this.userName = userName;
		this.contrasena = contrasena;
		this.confirContrasena = confirContrasena;
		this.enabled = enabled;
		this.rol = rol;
	}

	public TipDocumento getTipDocumento() {
		return tipDocumento;
	}

	public void setTipDocumento(TipDocumento tipDocuemnto) {
		this.tipDocumento = tipDocuemnto;
	}

	public Long getNumDocumento() {
		return numDocumento;
	}

	public void setNumDocumento(Long numDocumento) {
		this.numDocumento = numDocumento;
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
