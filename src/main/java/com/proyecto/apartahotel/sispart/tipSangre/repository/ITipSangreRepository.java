package com.proyecto.apartahotel.sispart.tipSangre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.apartahotel.sispart.tipSangre.entity.TipoSangre;

@Repository
public interface ITipSangreRepository extends JpaRepository<TipoSangre, Long> {

}
