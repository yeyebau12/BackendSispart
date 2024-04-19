package com.proyecto.apartahotel.sispart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.apartahotel.sispart.entity.Acompanantes;
import com.proyecto.apartahotel.sispart.entity.CheckIn;
import com.proyecto.apartahotel.sispart.entity.Habitacion;
import com.proyecto.apartahotel.sispart.entity.Huesped;
import com.proyecto.apartahotel.sispart.entity.TipDocumento;
import com.proyecto.apartahotel.sispart.repository.ICheckinRepository;
import com.proyecto.apartahotel.sispart.service.interfa.ICheckinService;

@Service
public class CheckinServiceImpl implements ICheckinService {

	@Autowired
	private ICheckinRepository checkinRepository;

	@Override
	@Transactional(readOnly = true)
	public List<CheckIn> findAll() {

		return checkinRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<CheckIn> findAll(Pageable pageable) {

		return checkinRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public CheckIn getOne(Long codCheckin) {

		return checkinRepository.findById(codCheckin).orElse(null);
	}

	@Override
	@Transactional
	public void save(CheckIn checkin) {

		checkinRepository.save(checkin);

	}

	@Override
	@Transactional
	public void delete(Long codCheckin) {

		checkinRepository.deleteById(codCheckin);

	}

	@Override
	@Transactional
	public boolean existsById(Long codCheckin) {

		return checkinRepository.existsById(codCheckin);
	}



}
