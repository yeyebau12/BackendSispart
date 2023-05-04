package com.proyecto.apartahotel.sispart.tipDocumento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.apartahotel.sispart.tipDocumento.entity.TipDocumento;

@Repository
public interface ITipDocumentoRepository extends JpaRepository<TipDocumento, Long> {

}
