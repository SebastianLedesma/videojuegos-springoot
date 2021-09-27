package com.springboot.backend.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="videojuegos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VideoJuego {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@NotEmpty(message="El titulo no puede estar vacio.")
	private String titulo;
	
	@Size(min=5,max=150,message="La descripcion debe tener entre 5 y 150 caracteres.")
	private String descripcion;
	
	private String imagen;
	
	@Min(value=10,message="El precio del juego debe ser como minimo 10.")
	@Max(value=10000,message="El precio del juego debe ser como maximo 10000.")
	private float precio;
	
	@Min(value=20,message="El stock del juego debe ser como minimo 20.")
	@Max(value=500,message="El stock del juego debe ser como maximo 500.")
	private short stock;
	
	@NotNull(message="Debe indicar la fecha de lanzamiento.")
	@FutureOrPresent(message="Debe ser la fecha actual o una a futuro")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name="fecha_lanzamiento")
	private Date fechaLanzamiento;
	
	private boolean activo = true;
	
	@NotNull(message="Se debe elegir el estudio que desarollo el juego.")
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="fk_estudio",nullable=false)
	private Estudio estudio;
	
	@NotNull(message="Se debe elegir una categoria para el juego.")
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="fk_categoria",nullable=false)
	private Categoria categoria;
	
}
