package com.proyecto.apartahotel.sispart.service.interfa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.proyecto.apartahotel.sispart.entity.Habitacion;
import com.proyecto.apartahotel.sispart.entity.Huesped;
import com.proyecto.apartahotel.sispart.entity.TipDocumento;

public interface IHuespedService {

	public List<Huesped> findAll();

	public Page<Huesped> findAll(Pageable pageable);

	public Huesped getOne(Long codHuesped);

	public Huesped getByTipoDocumentoAndNumDocumento(TipDocumento tipoDocumento, Long numDocumento);

	public void save(Huesped huesped);

	public void deleteByTipoDocumentoNumDocumento(TipDocumento tipoDocumento, Long numDocumento);

	public void delete(Long codHuesped);

	public boolean existsById(Long codHuesped);

	public boolean existsByTipoDocumentoAndNumDocumento(TipDocumento tipoDocumento, Long numDocumento);

}
