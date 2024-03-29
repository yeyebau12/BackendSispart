package com.proyecto.apartahotel.sispart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.apartahotel.sispart.entity.Producto;
import com.proyecto.apartahotel.sispart.repository.IProductoRepository;
import com.proyecto.apartahotel.sispart.service.interfa.IProductoService;

@Service
public class ProductoServiceImpl implements IProductoService {

	@Autowired
	private IProductoRepository productoRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Producto> findAll() {

		return productoRepository.findAll();
	}

	@Override
	public Producto findByCodProducto(Long codProducto) {

		return productoRepository.findById(codProducto).orElse(null);
	}
	
	@Override
	public Page<Producto> findAll(Pageable pageable) {

		return productoRepository.findAll(pageable);
	}

	@Override
	public void save(Producto producto) {

		productoRepository.save(producto);

	}

	@Override
	public void delete(Long codProducto) {

		productoRepository.deleteById(codProducto);

	}

	@Override
	public boolean existsById(Long codProducto) {

		return productoRepository.existsById(codProducto);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Producto> findProducto(String nombreProducto) {

		return productoRepository.findByNombreProductoStartingWithIgnoreCase(nombreProducto);
	}



}
