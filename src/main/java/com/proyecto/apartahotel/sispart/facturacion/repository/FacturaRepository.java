package com.proyecto.apartahotel.sispart.facturacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.apartahotel.sispart.facturacion.entity.Factura;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {

}
