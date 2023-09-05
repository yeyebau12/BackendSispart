package com.proyecto.apartahotel.sispart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyecto.apartahotel.sispart.entity.Checkin;
import com.proyecto.apartahotel.sispart.repository.ICheckinReposiotry;
import com.proyecto.apartahotel.sispart.service.interfa.ICheckinService;

@Service
public class CheckinServiceImpl implements ICheckinService {

	@Autowired
	private ICheckinReposiotry checkinRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Checkin> findAll() {

		return checkinRepository.findAll();
	}

	@Override
	@Transactional
	public void save(Checkin checkin) {
		checkinRepository.save(checkin);

	}

	@Override
	@Transactional
	public void delete(Long checkin) {
		checkinRepository.deleteById(checkin);

	}

	@Override
	public boolean existsById(Long codCheckin) {

		return checkinRepository.existsById(codCheckin);
	}

	@Override
	public Checkin findByCodCheckin(Long codCheckin) {

		return checkinRepository.findById(codCheckin).orElse(null);
	}

}
