package com.proyecto.apartahotel.sispart.service.interfa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.proyecto.apartahotel.sispart.entity.Acompanantes;
import com.proyecto.apartahotel.sispart.entity.CheckIn;
import com.proyecto.apartahotel.sispart.entity.Habitacion;
import com.proyecto.apartahotel.sispart.entity.Huesped;
import com.proyecto.apartahotel.sispart.entity.TipDocumento;


public interface ICheckinService {
	
	public List<CheckIn> findAll(); 
	
	public Page<CheckIn> findAll(Pageable pageable);
	
	public CheckIn getOne(Long codCheckin);
	
	public void save(CheckIn checkin);
	
	public void delete(Long codCheckin);
	
	public boolean existsById(Long codCheckin);
	
	

}
