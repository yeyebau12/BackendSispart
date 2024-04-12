package com.proyecto.apartahotel.sispart.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import javax.persistence.Column;

import javax.persistence.Entity;


import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "usuario_empleado")
public class UsuarioEmpleado implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_user_empleado")
	private Long codUserEmpleado;

	@OneToOne
	@JoinColumn(name = "cod_Empleado")
	@JsonIgnoreProperties(value = { "userEmpleado", "hibernateLazyInitializer", "handler" }, allowSetters = true)
	private Empleado empleado;

	@Column(name = "user_name", unique = true, length = 20, nullable = false)
	private String userName;

	@Column(nullable = false, length = 80)
	private String contrasena;

	@Column(name = "confirm_contrasena", length = 80, nullable = false)
	public String confirContrasena;

	private Boolean enabled = true;

	@Column(name = "create_user")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "GMT-5")
	private Date createUser;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "usuarios_empleados_roles", joinColumns = @JoinColumn(name = "cod_usuario_empleados"), inverseJoinColumns = @JoinColumn(name = "cod_role_empleado"), uniqueConstraints = {
			@UniqueConstraint(columnNames = { "cod_usuario_empleados", "cod_role_empleado" }) })
	private List<Role> rol;

	public UsuarioEmpleado() {
		
		this.rol = new ArrayList<>();

	}

	public UsuarioEmpleado(Long codUserEmpleado, Empleado empleado, String userName, String contrasena,
			String confirContrasena, List<Role> rol) {

		this.codUserEmpleado = codUserEmpleado;
		this.empleado = empleado;
		this.userName = userName;
		this.contrasena = contrasena;
		this.confirContrasena = confirContrasena;
		this.rol = rol;
	}

	public UsuarioEmpleado(Empleado empleado, String userName, String contrasena, String confirContrasena,
			List<Role> rol) {

		this.empleado = empleado;
		this.userName = userName;
		this.contrasena = contrasena;
		this.confirContrasena = confirContrasena;
		this.rol = rol;
	}

	@PrePersist
	public void prePersist() {
		createUser = new Date();
	}

	public Long getCodUserEmpleado() {
		return codUserEmpleado;
	}

	public void setCodUserEmpleado(Long codUserEmpleado) {
		this.codUserEmpleado = codUserEmpleado;
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

	private static final long serialVersionUID = 1L;

}
