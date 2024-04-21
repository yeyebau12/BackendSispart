package com.proyecto.apartahotel.sispart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.proyecto.apartahotel.sispart.entity.ProductsInt;
import com.proyecto.apartahotel.sispart.repository.IProductsIntRepository;
import com.proyecto.apartahotel.sispart.service.interfa.IProductsIntService;

@Service
public class ProductsIntServiceImpl implements IProductsIntService {

	@Autowired
	private IProductsIntRepository productsIntRepository;

	@Override
	public List<ProductsInt> findAll() {

		return productsIntRepository.findAll();
	}

	@Override
	public List<ProductsInt> findProducto(String nombreProducto) {

		return productsIntRepository.findByNombreProductoStartingWithIgnoreCase(nombreProducto);
	}

	@Override
	public Page<ProductsInt> findAll(Pageable pageable) {

		return productsIntRepository.findAll(pageable);
	}

	@Override
	public ProductsInt findByCodProducto(Long codProductInt) {

		return productsIntRepository.findById(codProductInt).orElse(null);
	}

	@Override
	public void save(ProductsInt productInt) {

		productsIntRepository.save(productInt);
	}

	@Override
	public void delete(Long codProductInt) {

		productsIntRepository.deleteById(codProductInt);

	}

	@Override
	public boolean existsById(Long codProductInt) {

		return productsIntRepository.existsById(codProductInt);
	}

}
