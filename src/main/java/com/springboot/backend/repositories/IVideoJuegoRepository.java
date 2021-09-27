package com.springboot.backend.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.backend.entities.VideoJuego;

@Repository
public interface IVideoJuegoRepository extends JpaRepository<VideoJuego, Long>{

	@Query(value="SELECT * FROM videojuegos WHERE videojuegos.activo = true", nativeQuery=true)
	List<VideoJuego> findAllByActivo();
	
	@Query(value="SELECT * FROM videojuegos WHERE videojuegos.id = :id AND videojuegos.activo = true", nativeQuery=true)
	Optional<VideoJuego> findByIdAndActivo(@Param("id") Long id);
	
	@Query(value="SELECT * FROM videojuegos WHERE videojuegos.titulo LIKE %:titulo% AND videojuegos.activo = true",nativeQuery=true)
	List<VideoJuego> findByTitle(@Param("titulo") String titulo);
}
