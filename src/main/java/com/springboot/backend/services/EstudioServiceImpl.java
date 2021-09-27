package com.springboot.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.backend.entities.Estudio;
import com.springboot.backend.repositories.IEstudioRepository;

@Service
public class EstudioServiceImpl implements IServicioBase<Estudio>{

	@Autowired
	private IEstudioRepository estudioRepo;
	
	@Transactional
	public List<Estudio> findAll() throws Exception {
		try {
			List<Estudio> listaEstudios = this.estudioRepo.findAll();
			return listaEstudios;
		}catch(Exception ex) {
			throw new Exception(ex.getMessage());
		}
	}

	@Transactional
	public Estudio findById(Long id) throws Exception {
		try {
			Optional<Estudio> optional = this.estudioRepo.findById(id);
			return optional.get();
		}catch(Exception ex) {
			throw new Exception(ex.getMessage());
		}
	}

	@Transactional
	public Estudio save(Estudio entity) throws Exception {
		try {
			Estudio estudio = this.estudioRepo.save(entity);
			return estudio;
		}catch(Exception ex) {
			throw new Exception(ex.getMessage());
		}
	}

	@Transactional
	public Estudio update(Estudio entity, Long id) throws Exception {
		try {
			Optional<Estudio> optional = this.estudioRepo.findById(id);
			Estudio estudio = optional.get();
			estudio = this.estudioRepo.save(entity);
			
			return estudio;
		}catch(Exception ex) {
			throw new Exception(ex.getMessage());
		}
	}

	@Transactional
	public boolean deleteById(Long id) throws Exception {
		try {
			Optional<Estudio> optional = this.estudioRepo.findById(id);
			if(optional.isPresent()) {
				Estudio estudio = optional.get();
				estudio.setActivo(!estudio.isActivo());
				this.estudioRepo.save(estudio);
			}else {
				throw new Exception();
			}
			return true;
		}catch(Exception ex) {
			throw new Exception(ex.getMessage());
		}
	}

}
