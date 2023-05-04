package com.proyecto.apartahotel.sispart.comentarios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.apartahotel.sispart.comentarios.entity.Comentarios;
import com.proyecto.apartahotel.sispart.comentarios.repository.IComentarioRepository;

@Service
public class ComentarioServiceImpl implements IComentarioService {

	@Autowired
	private IComentarioRepository comentarioRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Comentarios> findAll() {

		return comentarioRepository.findAll();
	}

	@Override
	@Transactional
	public void save(Comentarios comentario) {

		comentarioRepository.save(comentario);
	}

	@Override
	@Transactional
	public void delete(Long codComentario) {

		comentarioRepository.deleteById(codComentario);

	}

}
