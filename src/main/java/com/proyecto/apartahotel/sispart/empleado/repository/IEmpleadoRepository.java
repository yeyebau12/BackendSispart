package com.proyecto.apartahotel.sispart.empleado.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.apartahotel.sispart.empleado.entity.Empleado;
import com.proyecto.apartahotel.sispart.tipDocumento.entity.TipDocumento;

@Repository
public interface IEmpleadoRepository extends JpaRepository<Empleado, Long> {

	public boolean existsByTipDocumentoAndNumDocumento(TipDocumento tipDocumento, Long numDocumento);

	public Optional<Empleado> findByTipDocumentoAndNumDocumento(TipDocumento tipDocumento, Long numDocumento);
}
