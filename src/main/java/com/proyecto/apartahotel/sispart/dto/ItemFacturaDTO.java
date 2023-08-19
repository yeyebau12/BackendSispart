package com.proyecto.apartahotel.sispart.dto;

import com.proyecto.apartahotel.sispart.entity.Producto;

public class ItemFacturaDTO {

	private Integer cantidad;

	private Producto producto;

	public ItemFacturaDTO() {

	}


	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Double getSubtotal() {
		return cantidad.doubleValue() * producto.getPrecio();
	}

}
