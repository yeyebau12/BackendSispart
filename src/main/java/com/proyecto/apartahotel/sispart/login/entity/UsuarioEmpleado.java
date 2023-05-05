package com.proyecto.apartahotel.sispart.login.entity;

import java.io.Serializable;
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
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.Hibernate;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.proyecto.apartahotel.sispart.tipDocumento.entity.TipDocumento;

@Entity
@Table(name = "usuario_empleado")
public class UsuarioEmpleado implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_user_empleado")
	private Integer codUserEmpleado;

	// @JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" },
	// allowSetters = true)
	@ManyToOne(fetch = FetchType.LAZY) // , cascade = CascadeType.ALL)
	@JoinColumn(name = "cod_tip_documento", nullable = false)
	private TipDocumento tipDocumento;

	@Column(nullable = false)
	private Long numDocumento;

	@Column(name = "user_name", unique = true, length = 20, nullable = false)
	private String userName;

	@Column(nullable = false, length = 80)
	private String contraseña;

	@Column(name = "confirm_contraseña", length = 80, nullable = false)
	public String confirContraseña;

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

	}

	public UsuarioEmpleado(Integer codUserEmpleado, TipDocumento tipDocumento, Long numDocumento, String userName,
			String contraseña, String confirContraseña, Boolean enabled, Date createUser, List<Role> rol) {

		this.codUserEmpleado = codUserEmpleado;
		this.tipDocumento = tipDocumento;
		this.numDocumento = numDocumento;
		this.userName = userName;
		this.contraseña = contraseña;
		this.confirContraseña = confirContraseña;
		this.enabled = enabled;
		this.createUser = createUser;
		this.rol = rol;
	}

	public UsuarioEmpleado(TipDocumento tipDocumento, Long numDocumento, String userName, String contraseña,
			String confirContraseña, List<Role> rol) {

		this.tipDocumento = tipDocumento;
		this.numDocumento = numDocumento;
		this.userName = userName;
		this.contraseña = contraseña;
		this.confirContraseña = confirContraseña;
		this.rol = rol;
	}

	@PrePersist
	public void prePersist() {
		createUser = new Date();
	}

	public Integer getCodUserEmpleado() {
		return codUserEmpleado;
	}

	public void setCodUserEmpleado(Integer codUserEmpleado) {
		this.codUserEmpleado = codUserEmpleado;
	}

	public TipDocumento getTipDocumento() {
		return tipDocumento;
	}

	public void setTipDocuemnto(TipDocumento tipDocuemnto) {
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

	private static final long serialVersionUID = 1L;

}
