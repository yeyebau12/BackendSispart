package com.proyecto.apartahotel.sispart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.apartahotel.sispart.entity.CheckIn;
import com.proyecto.apartahotel.sispart.entity.Habitacion;
import com.proyecto.apartahotel.sispart.entity.Huesped;

@Repository
public interface ICheckinRepository extends JpaRepository<CheckIn, Long>{
	 

}
