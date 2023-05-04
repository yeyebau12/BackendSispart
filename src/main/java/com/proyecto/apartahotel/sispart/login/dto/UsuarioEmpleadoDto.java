package com.proyecto.apartahotel.sispart.login.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.proyecto.apartahotel.sispart.empleado.entity.Empleado;
import com.proyecto.apartahotel.sispart.login.entity.Role;
import com.proyecto.apartahotel.sispart.tipDocumento.entity.TipDocumento;

public class UsuarioEmpleadoDto {

	@NotNull
	private TipDocumento tipDocumento;

	@NotNull
	private Long numDocumento;

	@NotEmpty
	private String userName;

	@NotEmpty
	@Size(min = 4, max = 10)
	private String contraseña;

	@NotEmpty
	@Size(min = 4, max = 10)
	private String confirContraseña;

	private Boolean enabled = true;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "GMT-5")
	private Date createUser;

	private List<Role> rol;

	public UsuarioEmpleadoDto() {

	}

	public UsuarioEmpleadoDto(@NotEmpty TipDocumento tipDocumento, @NotEmpty Long numDocumento,
			@NotEmpty String userName, @NotEmpty @Size(min = 4, max = 10) String contraseña,
			@NotEmpty @Size(min = 4, max = 10) String confirContraseña, Boolean enabled, Date createUser,
			List<Role> rol) {
		super();
		this.tipDocumento = tipDocumento;
		this.numDocumento = numDocumento;
		this.userName = userName;
		this.contraseña = contraseña;
		this.confirContraseña = confirContraseña;
		this.enabled = enabled;
		this.createUser = createUser;
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

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public String getConfirContraseña() {
		return confirContraseña;
	}

	public void setConfirContraseña(String confirContraseña) {
		this.confirContraseña = confirContraseña;
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
