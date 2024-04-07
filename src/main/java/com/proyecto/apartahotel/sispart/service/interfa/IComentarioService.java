package com.proyecto.apartahotel.sispart.service.interfa;

import java.util.List;

import com.proyecto.apartahotel.sispart.entity.Comentarios;

public interface IComentarioService {

	public List<Comentarios> findAll();
	
	public Comentarios getOne (Long codComentario);

	public void save(Comentarios empleado);

	public void delete(Long codComentario);
}
