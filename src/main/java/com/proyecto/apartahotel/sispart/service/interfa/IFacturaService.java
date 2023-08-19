package com.proyecto.apartahotel.sispart.service.interfa;

import com.proyecto.apartahotel.sispart.entity.Factura;

public interface IFacturaService {

	public Factura findFacturaById(Long codFactura);

	public void saveFactura(Factura factura);

	public void deleteFacturaById(Long codFactura);

	public boolean existsFacturaById(Long codFactura);

}
