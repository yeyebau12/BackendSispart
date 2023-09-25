package com.proyecto.apartahotel.sispart.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.apartahotel.sispart.entity.Habitacion;
import com.proyecto.apartahotel.sispart.entity.Huesped;
import com.proyecto.apartahotel.sispart.entity.TipDocumento;

@Repository
public interface IHuespedRepository extends JpaRepository<Huesped, Long> {
	Optional<Huesped> findByTipoDocumentoAndNumDocumento(TipDocumento tipoDocumento, Long numDocumento);

	Optional<Huesped> findByNumDocumento(Long numDocumento);

	boolean existsByTipoDocumentoAndNumDocumento(TipDocumento tipoDocumento, Long numDocumento);

	void deleteByTipoDocumentoAndNumDocumento(TipDocumento tipoDocumento, Long numDocumento);
	
  
 
}

