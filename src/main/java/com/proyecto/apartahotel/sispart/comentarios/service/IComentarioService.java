package com.proyecto.apartahotel.sispart.comentarios.service;

import java.util.List;

import com.proyecto.apartahotel.sispart.comentarios.entity.Comentarios;

public interface IComentarioService {

	public List<Comentarios> findAll();

	public void save(Comentarios empleado);

	public void delete(Long codComentario);
}
