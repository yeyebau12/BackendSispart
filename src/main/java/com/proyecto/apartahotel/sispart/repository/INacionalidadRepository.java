package com.proyecto.apartahotel.sispart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.apartahotel.sispart.entity.Nacionalidad;

@Repository
public interface INacionalidadRepository extends JpaRepository<Nacionalidad, Long> {

}
