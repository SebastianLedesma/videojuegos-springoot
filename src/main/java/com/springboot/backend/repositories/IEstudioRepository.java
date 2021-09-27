package com.springboot.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.backend.entities.Estudio;

@Repository
public interface IEstudioRepository extends JpaRepository<Estudio, Long>{

}
