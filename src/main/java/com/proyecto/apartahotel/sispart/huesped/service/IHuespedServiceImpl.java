package com.proyecto.apartahotel.sispart.huesped.service;

import java.util.List;
import java.util.Optional;

import com.proyecto.apartahotel.sispart.huesped.entity.Huesped;
import com.proyecto.apartahotel.sispart.tipDocumento.entity.TipDocumento;

public interface IHuespedServiceImpl {

	public List<Huesped> findAll();

	public Huesped getOne(Long codHuesped);

	public Huesped getByTipoDocumentoAndNumDocumento(TipDocumento tipoDocumento, Long numDocumento);

	public void save(Huesped huesped);

	public void deleteByTipoDocumentoNumDocumento(TipDocumento tipoDocumento, Long numDocumento);

	public void delete(Long codHuesped);

	public boolean existsById(Long codHuesped);

	public boolean existsByTipoDocumentoAndNumDocumento(TipDocumento tipoDocumento, Long numDocumento);

}
