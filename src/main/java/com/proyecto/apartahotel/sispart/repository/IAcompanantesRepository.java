package com.proyecto.apartahotel.sispart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.apartahotel.sispart.entity.Acompanantes;
import com.proyecto.apartahotel.sispart.entity.TipDocumento;

@Repository
public interface IAcompanantesRepository extends JpaRepository<Acompanantes, Long> {
	
	boolean existsByTipoDocumentoAndNumDocumento(TipDocumento tipoDocumento, Long numDocumento);

}
