package com.proyecto.apartahotel.sispart.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.apartahotel.sispart.entity.Factura;
import com.proyecto.apartahotel.sispart.repository.IFacturaRepository;
import com.proyecto.apartahotel.sispart.service.interfa.IFacturaService;

@Service
public class FacturaServiceImpl implements IFacturaService {

	@Autowired
	private IFacturaRepository facturaRepository;

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
