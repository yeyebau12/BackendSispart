package com.proyecto.apartahotel.sispart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.apartahotel.sispart.entity.Comentarios;
import com.proyecto.apartahotel.sispart.repository.IComentarioRepository;
import com.proyecto.apartahotel.sispart.service.interfa.IComentarioService;

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
	@Transactional(readOnly = true)
	public Comentarios getOne(Long codComentario) {
		
		return comentarioRepository.findById(codComentario).orElse(null);
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
