package com.proyecto.apartahotel.sispart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.apartahotel.sispart.entity.Producto;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Long> {

	public List<Producto> findByNombreProductoStartingWithIgnoreCase(String nombreProducto);

}
