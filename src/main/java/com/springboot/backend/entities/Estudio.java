package com.springboot.backend.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="estudios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Estudio {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	
	private boolean activo = true;
	
	@OneToMany(mappedBy="estudio")
	private List<VideoJuego> videoJuegos;
	
}
