package com.springboot.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.backend.entities.VideoJuego;
import com.springboot.backend.repositories.IVideoJuegoRepository;

@Service
public class VideoJuegoServiceImpl implements IServicioBase<VideoJuego>{

	@Autowired
	private IVideoJuegoRepository videjuegoRepo;
	
	
	@Transactional
	public List<VideoJuego> findAll() throws Exception {
		try {
			List<VideoJuego> listaVideJuegos = this.videjuegoRepo.findAll();
			return listaVideJuegos;
		}catch(Exception ex) {
			throw new Exception(ex.getMessage());
		}
		
	}

	@Transactional
	public VideoJuego findById(Long id) throws Exception {
		try {
			Optional<VideoJuego> juego = this.videjuegoRepo.findById(id);
			return juego.get();
		}catch(Exception ex) {
			throw new Exception(ex.getMessage());
		}
	}

	@Transactional
	public VideoJuego save(VideoJuego entity) throws Exception {
		try {
			VideoJuego videoJuego = this.videjuegoRepo.save(entity);
			return videoJuego;
		}catch(Exception ex) {
			throw new Exception(ex.getMessage());
		}
	}


	@Transactional
	public VideoJuego update(VideoJuego entity, Long id) throws Exception {
		try {
			Optional<VideoJuego> optional = this.videjuegoRepo.findById(id);
			VideoJuego videoJuego = optional.get();
			videoJuego = this.videjuegoRepo.save(entity);
			
			return videoJuego;
		}catch(Exception ex) {
			throw new Exception(ex.getMessage());
		}
	}

	
	@Transactional
	public boolean deleteById(Long id) throws Exception {
		try {
			Optional<VideoJuego> optional = this.videjuegoRepo.findById(id);
			if(optional.isPresent()) {
				VideoJuego videoJuego = optional.get();
				videoJuego.setActivo(!videoJuego.isActivo());
				this.videjuegoRepo.save(videoJuego);
			}else {
				throw new Exception();
			}
			return true;
		}catch(Exception ex) {
			throw new Exception(ex.getMessage());
		}
	}
	
	
	@Transactional
	public List<VideoJuego> findAllByActivo() throws Exception{
		try {
			List<VideoJuego> lista = this.videjuegoRepo.findAllByActivo();
			return lista;
		}catch(Exception ex) {
			throw new Exception(ex.getMessage());
		}
	}
	
	@Transactional
	public VideoJuego findByIdAndActivo(Long id) throws Exception{
		try {
			Optional<VideoJuego> optional = this.videjuegoRepo.findByIdAndActivo(id);
			return optional.get();
		}catch(Exception ex) {
			throw new Exception(ex.getMessage());
		}
	}
	
	@Transactional
	public List<VideoJuego> findByTitle(String titulo) throws Exception{
		try {
			List<VideoJuego> listaVideoJuegos = this.videjuegoRepo.findByTitle(titulo);
			return listaVideoJuegos;
		}catch(Exception ex) {
			throw new Exception(ex.getMessage());
		}
	}

}
