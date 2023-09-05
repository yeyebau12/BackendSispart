package com.proyecto.apartahotel.sispart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.apartahotel.sispart.entity.Checkout;
import com.proyecto.apartahotel.sispart.repository.ICheckinReposiotry;
import com.proyecto.apartahotel.sispart.repository.ICheckoutRepository;
import com.proyecto.apartahotel.sispart.service.interfa.ICheckoutService;

@Service
public class CheckoutServiceImpl implements ICheckoutService {

	@Autowired
	public ICheckoutRepository checkoutRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Checkout> findAll() {

		return checkoutRepository.findAll();
	}

	@Override
	@Transactional
	public void save(Checkout checkout) {
		checkoutRepository.save(checkout);

	}

	@Override
	@Transactional
	public void delete(Long checkout) {
		checkoutRepository.deleteById(checkout);

	}

	@Override
	public Checkout findByCodCheckout(Long codCheckout) {

		return checkoutRepository.findById(codCheckout).orElse(null);
	}

	@Override
	public boolean existsById(Long codCheckout) {

		return checkoutRepository.existsById(codCheckout);
	}

}
