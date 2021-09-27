package com.springboot.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.backend.entities.Categoria;

@Repository
public interface ICategoriaRepository extends JpaRepository<Categoria, Long>{

}
