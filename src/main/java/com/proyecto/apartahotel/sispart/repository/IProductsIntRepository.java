package com.proyecto.apartahotel.sispart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.proyecto.apartahotel.sispart.entity.ProductsInt;

@Repository
public interface IProductsIntRepository extends JpaRepository<ProductsInt, Long> {
	
	public List<ProductsInt> findByNombreProductoStartingWithIgnoreCase(String nombreProducto);

}
