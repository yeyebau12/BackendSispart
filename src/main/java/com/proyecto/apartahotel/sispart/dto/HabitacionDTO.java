package com.proyecto.apartahotel.sispart.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


import com.proyecto.apartahotel.sispart.entity.Factura;

public class HabitacionDTO {

	@NotEmpty
	private String nombreHabitacion;
	@NotEmpty
	private String descripHabitacion;
	@NotNull
	private Integer numHabitacion;
	@NotNull
	private Integer pisoHabitacion;
	@NotNull
	private Integer maxPersonasDisponibles;
	@NotNull
	private Double precioDia;

	private String estadoHabitacion;
	private String imagenHabitacion;

	//private Factura facturas;

	public HabitacionDTO() {

		//this.facturas = new Factura();
	}

	public HabitacionDTO(@NotEmpty String nombreHabitacion, @NotEmpty String descripHabitacion,
			@NotNull Integer numHabitacion, @NotNull Integer pisoHabitacion, @NotNull Integer maxPersonasDisponibles,
			@NotNull Double precioDia, String estadoHabitacion, String imagenHabitacion) {

		this.nombreHabitacion = nombreHabitacion;
		this.descripHabitacion = descripHabitacion;
		this.numHabitacion = numHabitacion;
		this.pisoHabitacion = pisoHabitacion;
		this.maxPersonasDisponibles = maxPersonasDisponibles;
		this.precioDia = precioDia;
		this.estadoHabitacion = estadoHabitacion;
		this.imagenHabitacion = imagenHabitacion;
	}

	public String getNombreHabitacion() {
		return nombreHabitacion;
	}

	public void setNombreHabitacion(String nombreHabitacion) {
		this.nombreHabitacion = nombreHabitacion;
	}

	public String getDescripHabitacion() {
		return descripHabitacion;
	}

	public void setDescripHabitacion(String descripHabitacion) {
		this.descripHabitacion = descripHabitacion;
	}

	public Integer getNumHabitacion() {
		return numHabitacion;
	}

	public void setNumHabitacion(Integer numHabitacion) {
		this.numHabitacion = numHabitacion;
	}

	public Integer getPisoHabitacion() {
		return pisoHabitacion;
	}

	public void setPisoHabitacion(Integer pisoHabitacion) {
		this.pisoHabitacion = pisoHabitacion;
	}

	public Integer getMaxPersonasDisponibles() {
		return maxPersonasDisponibles;
	}

	public void setMaxPersonasDisponibles(Integer maxPersonasDisponibles) {
		this.maxPersonasDisponibles = maxPersonasDisponibles;
	}

	public Double getPrecioDia() {
		return precioDia;
	}

	public void setPrecioDia(Double precioDia) {
		this.precioDia = precioDia;
	}

	public String getEstadoHabitacion() {
		return estadoHabitacion;
	}

	public void setEstadoHabitacion(String estadoHabitacion) {
		this.estadoHabitacion = estadoHabitacion;
	}

	public String getImagenHabitacion() {
		return imagenHabitacion;
	}

	public void setImagenHabitacion(String imagenHabitacion) {
		this.imagenHabitacion = imagenHabitacion;
	}

/*	public Factura getFacturas() {
		return facturas;
	}

	public void setFacturas(Factura facturas) {
		this.facturas = facturas;
	}*/

}
