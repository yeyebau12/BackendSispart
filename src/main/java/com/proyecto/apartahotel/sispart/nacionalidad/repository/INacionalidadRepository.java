package com.proyecto.apartahotel.sispart.nacionalidad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.apartahotel.sispart.nacionalidad.entity.Nacionalidad;

@Repository
public interface INacionalidadRepository extends JpaRepository<Nacionalidad, Long> {

}
