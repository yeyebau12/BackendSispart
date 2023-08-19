package com.proyecto.apartahotel.sispart.service.interfa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.proyecto.apartahotel.sispart.entity.Producto;

public interface IProductoService {

	public List<Producto> findAll();

	public List<Producto> findProducto(String nombreProducto);
	
	public Page<Producto> findAll(Pageable pageable);

	public Producto findByCodProducto(Long codProducto);

	public void save(Producto producto);

	public void delete(Long codProducto);

	public boolean existsById(Long codProducto);
}
