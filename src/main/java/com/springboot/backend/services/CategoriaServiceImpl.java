package com.springboot.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.backend.entities.Categoria;
import com.springboot.backend.repositories.ICategoriaRepository;

@Service
public class CategoriaServiceImpl implements IServicioBase<Categoria>{

	@Autowired
	private ICategoriaRepository categoriaRepo;
	
	public List<Categoria> findAll() throws Exception {
		try {
			List<Categoria> listaCategorias = this.categoriaRepo.findAll();
			return listaCategorias;
		}catch(Exception ex) {
			throw new Exception(ex.getMessage());
		}
	}

	public Categoria findById(Long id) throws Exception {
		try {
			Optional<Categoria> optional = this.categoriaRepo.findById(id);
			return optional.get();
		}catch(Exception ex) {
			throw new Exception(ex.getMessage());
		}
	}

	public Categoria save(Categoria entity) throws Exception {
		try {
			Categoria categoria = this.categoriaRepo.save(entity);
			return categoria;
		}catch(Exception ex) {
			throw new Exception(ex.getMessage());
		}
	}

	public Categoria update(Categoria entity, Long id) throws Exception {
		try {
			Optional<Categoria> optional = this.categoriaRepo.findById(id);
			Categoria categoria = optional.get();
			categoria = this.categoriaRepo.save(entity);
			
			return categoria;
		}catch(Exception ex) {
			throw new Exception(ex.getMessage());
		}
	}

	public boolean deleteById(Long id) throws Exception {
		try {
			Optional<Categoria> optional = this.categoriaRepo.findById(id);
			if(optional.isPresent()) {
				Categoria categoria = optional.get();
				categoria.setActivo(!categoria.isActivo());
				this.categoriaRepo.save(categoria);
			}else {
				throw new Exception();
			}
			return true;
		}catch(Exception ex) {
			throw new Exception(ex.getMessage());
		}
	}

}
