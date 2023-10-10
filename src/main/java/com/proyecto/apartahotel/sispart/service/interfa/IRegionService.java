package com.proyecto.apartahotel.sispart.service.interfa;

import java.util.List;

import com.proyecto.apartahotel.sispart.entity.Region;

public interface IRegionService {

	public List<Region> findAll();

	

	public List<Region> findAllByNacion(Long codNacionacionalidad);
}
