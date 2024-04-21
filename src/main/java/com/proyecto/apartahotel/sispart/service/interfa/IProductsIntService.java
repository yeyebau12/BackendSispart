package com.proyecto.apartahotel.sispart.service.interfa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.proyecto.apartahotel.sispart.entity.ProductsInt;



public interface IProductsIntService {
	
	public List<ProductsInt> findAll();

	public List<ProductsInt> findProducto(String nombreProducto);
	
	public Page<ProductsInt> findAll(Pageable pageable);

	public ProductsInt findByCodProducto(Long codProductInt);

	public void save(ProductsInt productInt);

	public void delete(Long codProductInt);

	public boolean existsById(Long codProductInt);

}
