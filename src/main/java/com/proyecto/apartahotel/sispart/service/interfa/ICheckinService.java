package com.proyecto.apartahotel.sispart.service.interfa;

import java.util.List;

import com.proyecto.apartahotel.sispart.entity.Checkin;
import com.proyecto.apartahotel.sispart.entity.Habitacion;

public interface ICheckinService {

	public List<Checkin> findAll();

	public void save(Checkin checkin);

	public void delete(Long checkin);

	public Checkin findByCodCheckin(Long codCheckin);

	public boolean existsById(Long codCheckin);

}
