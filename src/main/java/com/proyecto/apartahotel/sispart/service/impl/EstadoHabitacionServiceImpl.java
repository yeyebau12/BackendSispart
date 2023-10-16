package com.proyecto.apartahotel.sispart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.apartahotel.sispart.entity.EstadoHabitacion;

import com.proyecto.apartahotel.sispart.repository.IEstadoHabitacionRepository;
import com.proyecto.apartahotel.sispart.service.interfa.IEstadoHabitacionService;

@Service
public class EstadoHabitacionServiceImpl implements IEstadoHabitacionService {
	
	@Autowired
	private  IEstadoHabitacionRepository estadoHabitacionRepository;

	@Override
	public List<EstadoHabitacion> findAll() {
		
		return estadoHabitacionRepository.findAll();
	}
	
	

}
