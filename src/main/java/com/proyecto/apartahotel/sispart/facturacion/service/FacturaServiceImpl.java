package com.proyecto.apartahotel.sispart.facturacion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.apartahotel.sispart.facturacion.entity.Factura;
import com.proyecto.apartahotel.sispart.facturacion.repository.FacturaRepository;

@Service
public class FacturaServiceImpl implements IFacturaService {

	@Autowired
	private FacturaRepository facturaRepository;

	@Override
	@Transactional(readOnly = true)
	public Factura findFacturaById(Long codFactura) {

		return facturaRepository.findById(codFactura).orElse(null);
	}

	@Override
	public void saveFactura(Factura factura) {

		facturaRepository.save(factura);
	}

	@Override
	public void deleteFacturaById(Long codFactura) {

		facturaRepository.deleteById(codFactura);

	}

	@Override
	public boolean existsFacturaById(Long codFactura) {

		return facturaRepository.existsById(codFactura);
	}

}
