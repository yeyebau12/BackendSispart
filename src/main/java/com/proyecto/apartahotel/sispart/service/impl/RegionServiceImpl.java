package com.proyecto.apartahotel.sispart.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.apartahotel.sispart.entity.Region;
import com.proyecto.apartahotel.sispart.repository.IRegionRepository;
import com.proyecto.apartahotel.sispart.service.interfa.IRegionService;

@Service
public class RegionServiceImpl implements IRegionService {

	@Autowired
	private IRegionRepository regioRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Region> findAll() {

		return regioRepository.findAll();
	}

	
	@Override
	@Transactional(readOnly = true)
	public List<Region> findAllByNacion(Long codNacionacionalidad) {

		List<Region> regionRespuesta = new ArrayList<>();
		List<Region> region = regioRepository.findAll();

		for (int i = 0; i < region.size(); i++) {

			if (region.get(i).getNacionalidad().getCodNacion() == codNacionacionalidad) {

				regionRespuesta.add(region.get(i));
			}
		}
		return regionRespuesta;
	}

}
