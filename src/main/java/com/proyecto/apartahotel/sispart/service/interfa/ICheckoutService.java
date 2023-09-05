package com.proyecto.apartahotel.sispart.service.interfa;

import java.util.List;

import com.proyecto.apartahotel.sispart.entity.Checkout;

public interface ICheckoutService {

	public List<Checkout> findAll();

	public void save(Checkout checkout);

	public void delete(Long checkout);

	public Checkout findByCodCheckout(Long codCheckout);

	public boolean existsById(Long codCheckout);

}
