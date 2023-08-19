package com.proyecto.apartahotel.sispart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.apartahotel.sispart.entity.Empleado;
import com.proyecto.apartahotel.sispart.entity.TipDocumento;

@Repository
public interface IEmpleadoRepository extends JpaRepository<Empleado, Long> {

	public boolean existsByTipDocumentoAndNumDocumento(TipDocumento tipDocumento, Long numDocumento);

	public Optional<Empleado> findByTipDocumentoAndNumDocumento(TipDocumento tipDocumento, Long numDocumento);
}
