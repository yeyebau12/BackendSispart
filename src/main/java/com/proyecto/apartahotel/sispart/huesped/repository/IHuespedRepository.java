package com.proyecto.apartahotel.sispart.huesped.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.apartahotel.sispart.huesped.entity.Huesped;
import com.proyecto.apartahotel.sispart.tipDocumento.entity.TipDocumento;

@Repository
public interface IHuespedRepository extends JpaRepository<Huesped, Long> {
	Optional<Huesped> findByTipoDocumentoAndNumDocumento(TipDocumento tipoDocumento, Long numDocumento);

	Optional<Huesped> findByNumDocumento(Long numDocumento);

	boolean existsByTipoDocumentoAndNumDocumento(TipDocumento tipoDocumento, Long numDocumento);

	void deleteByTipoDocumentoAndNumDocumento(TipDocumento tipoDocumento, Long numDocumento);

}
