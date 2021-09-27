package com.springboot.backend.services;

import java.util.List;

public interface IServicioBase<E> {

	List<E> findAll() throws Exception;
	
	E findById(Long id) throws Exception;
	
	E save(E entity) throws Exception;
	
	E update(E entity,Long id) throws Exception;
	
	boolean deleteById(Long id) throws Exception;
}
