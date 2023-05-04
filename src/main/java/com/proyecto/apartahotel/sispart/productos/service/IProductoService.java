package com.proyecto.apartahotel.sispart.productos.service;

import java.util.List;

import com.proyecto.apartahotel.sispart.productos.entity.Producto;

public interface IProductoService {

	public List<Producto> findAll();

	public List<Producto> findProducto(String nombreProducto);

	public Producto findByCodProducto(Long codProducto);

	public void save(Producto producto);

	public void delete(Long codProducto);

	public boolean existsById(Long codProducto);
}
