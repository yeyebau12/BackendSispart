package com.proyecto.apartahotel.sispart.empleado.dto;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.proyecto.apartahotel.sispart.genero.entity.Sexo;
import com.proyecto.apartahotel.sispart.tipDocumento.entity.TipDocumento;
import com.proyecto.apartahotel.sispart.tipSangre.entity.TipoSangre;

public class EmpleadoDTO {

	@NotEmpty
	private String nombre;
	@NotEmpty
	private String apellido;
	@NotNull
	private TipDocumento tipDocumento;
	@NotNull
	private Long numDocumento;
	@NotNull
	private Integer edad;
	@NotNull
	private Long numTelefono;
	@NotEmpty
	@Email
	private String correo;
	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@JsonFormat(pattern = "dd-MM-yyyy", timezone = "GMT-5")
	private Date fechaNacimiento;
	@NotEmpty
	private String direccion;
	@NotEmpty
	private String nomContactoEmergencia;
	@NotNull
	private Long numContactoEmergencia;
	@NotEmpty
	private String eps;
	@NotEmpty
	private String arl;
	@NotNull
	private Sexo sexo;
	@NotNull
	private TipoSangre tipoSangre;

	private String fotoEmpleado;

	public EmpleadoDTO() {

	}

	public EmpleadoDTO(@NotEmpty String nombre, @NotEmpty String apellido, @NotEmpty TipDocumento tipDocumento,
			@NotNull Long numDocumento, @NotNull Integer edad, @NotNull Long numTelefono,
			@NotEmpty @Email String correo, @NotEmpty Date fechaNacimiento, @NotEmpty String direccion,
			@NotEmpty String nomContactoEmergencia, @NotNull Long numContactoEmergencia, @NotEmpty String eps,
			@NotEmpty String arl, @NotEmpty Sexo sexo, @NotEmpty TipoSangre tipoSangre, @NotEmpty String fotoEmpleado) {

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
		this.fotoEmpleado = fotoEmpleado;
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

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public Long getNumTelefono() {
		return numTelefono;
	}

	public void setNumTelefono(Long numTelefono) {
		this.numTelefono = numTelefono;
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

}
