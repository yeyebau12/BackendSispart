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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "empleados")
public class Empleado implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codEmpleado;

	@Column(length = 30, nullable = false)
	private String nombre;
	@Column(length = 30, nullable = false)
	private String apellido;
	@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" }, allowSetters = true)
	@ManyToOne(fetch = FetchType.LAZY) // , cascade = CascadeType.ALL)
	@JoinColumn(name = "tip_documento", nullable = false)
	private TipDocumento tipDocumento;
	@Column(name = "num_documento", length = 20, nullable = false)
	private Long numDocumento;
	@Column(length = 10)
	private Integer edad;
	@Column(name = "num_telefono", length = 20, nullable = false)
	private Long numTelefono;
	@Column(nullable = false)
	private String correo;
	@Column(name = "fecha_nacimento", nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT-5")
	private Date fechaNacimiento;
	@Column(length = 50, nullable = false)
	private String direccion;
	@Column(name = "nom_contacto_emergencia", length = 30)
	private String nomContactoEmergencia;
	@Column(name = "num_contacto_emergencia", length = 20)
	private Long numContactoEmergencia;
	@Column(length = 30, nullable = false)
	private String eps;
	@Column(length = 30, nullable = false)
	private String arl;
	@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" }, allowSetters = true)
	@ManyToOne(fetch = FetchType.LAZY) // , cascade = CascadeType.ALL)
	@JoinColumn(name="cod_sexo",nullable = false)
	private Sexo sexo;
	@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" }, allowSetters = true)
	@ManyToOne(fetch = FetchType.LAZY) // , cascade = CascadeType.ALL)
	@JoinColumn(name = "tipo_sangre", nullable = false)
	private TipoSangre tipoSangre;
	@Column(name = "foto_empleado")
	private String fotoEmpleado;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "empleado", cascade = CascadeType.ALL)
	@JsonIgnoreProperties(value = { "empleado", "hibernateLazyInitializer", "handler" }, allowSetters = true)
	private List<Actividad> actividad;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT-5")
	private Date fechaIngreso;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT-5")
	private Date fechaSalida;
	
	@OneToOne (cascade = CascadeType.ALL, mappedBy = "empleado")
	@JsonIgnoreProperties(value = { "empleado", "hibernateLazyInitializer", "handler" }, allowSetters = true)
	private UsuarioEmpleado userEmpleado;
	
	

	public Empleado() {

		this.actividad = new ArrayList<>();
		this.userEmpleado = new UsuarioEmpleado();
	}

	

	public Empleado(Long codEmpleado, String nombre, String apellido, TipDocumento tipDocumento, Long numDocumento,
			Integer edad, Long numTelefono, String correo, Date fechaNacimiento, String direccion,
			String nomContactoEmergencia, Long numContactoEmergencia, String eps, String arl, Sexo sexo,
			TipoSangre tipoSangre, Date fechaIngreso, Date fechaSalida) {
		super();
		this.codEmpleado = codEmpleado;
		this.nombre = nombre;
		this.apellido = apellido;
		this.tipDocumento = tipDocumento;
		this.numDocumento = numDocumento;
		this.edad = edad;
		this.numTelefono = numTelefono;
		this.correo = correo;
		this.fechaNacimiento = fechaNacimiento;
		this.direccion = direccion;
		this.nomContactoEmergencia = nomContactoEmergencia;
		this.numContactoEmergencia = numContactoEmergencia;
		this.eps = eps;
		this.arl = arl;
		this.sexo = sexo;
		this.tipoSangre = tipoSangre;
		this.fechaIngreso = fechaIngreso;
		this.fechaSalida = fechaSalida;
	}

	public Empleado(String nombre, String apellido, TipDocumento tipDocumento, Long numDocumento, Integer edad,
			Long numTelefono, String correo, Date fechaNacimiento, String direccion, String nomContactoEmergencia,
			Long numContactoEmergencia, String eps, String arl, Sexo sexo, TipoSangre tipoSangre, Date fechaIngreso,
			Date fechaSalida) {
		
		this.nombre = nombre;
		this.apellido = apellido;
		this.tipDocumento = tipDocumento;
		this.numDocumento = numDocumento;
		this.edad = edad;
		this.numTelefono = numTelefono;
		this.correo = correo;
		this.fechaNacimiento = fechaNacimiento;
		this.direccion = direccion;
		this.nomContactoEmergencia = nomContactoEmergencia;
		this.numContactoEmergencia = numContactoEmergencia;
		this.eps = eps;
		this.arl = arl;
		this.sexo = sexo;
		this.tipoSangre = tipoSangre;
		this.fechaIngreso = fechaIngreso;
		this.fechaSalida = fechaSalida;
	}



	public Long getCodEmpleado() {
		return codEmpleado;
	}

	public void setCodEmpleado(Long cod_empleado) {
		this.codEmpleado = cod_empleado;
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

	public TipDocumento getTipDocumento() {
		return tipDocumento;
	}

	public void setTipDocumento(TipDocumento tipDocumento) {
		this.tipDocumento = tipDocumento;
	}

	public Long getNumDocumento() {
		return numDocumento;
	}

	public void setNumDocumento(Long numDocumento) {
		this.numDocumento = numDocumento;
	}

	public Long getNumTelefono() {
		return numTelefono;
	}

	public void setNumTelefono(Long numTelefono) {
		this.numTelefono = numTelefono;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
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

	public String getEps() {
		return eps;
	}

	public void setEps(String eps) {
		this.eps = eps;
	}

	public String getArl() {
		return arl;
	}

	public void setArl(String arl) {
		this.arl = arl;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public TipoSangre getTipoSangre() {
		return tipoSangre;
	}

	public void setTipoSangre(TipoSangre tipoSangre) {
		this.tipoSangre = tipoSangre;
	}

	public String getFotoEmpleado() {
		return fotoEmpleado;
	}

	public void setFotoEmpleado(String fotoEmpleado) {
		this.fotoEmpleado = fotoEmpleado;
	}

	public List<Actividad> getActividad() {
		return actividad;
	}

	public void setActividad(List<Actividad> actividad) {
		this.actividad = actividad;
	}
	
	

	public Date getFechaIngreso() {
		return fechaIngreso;
	}



	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}



	public Date getFechaSalida() {
		return fechaSalida;
	}



	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
	
	



	public UsuarioEmpleado getUserEmpleado() {
		return userEmpleado;
	}



	public void setUserEmpleado(UsuarioEmpleado userEmpleado) {
		this.userEmpleado = userEmpleado;
	}





	private static final long serialVersionUID = 1L;
}
