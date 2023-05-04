package com.proyecto.apartahotel.sispart.genero.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.apartahotel.sispart.genero.entity.Sexo;

@Repository
public interface ISexoRepository extends JpaRepository<Sexo, Long> {

	
}
