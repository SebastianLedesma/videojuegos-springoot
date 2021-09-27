package com.springboot.backend.controllers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.List;

import javax.imageio.ImageIO;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.backend.entities.VideoJuego;
import com.springboot.backend.services.CategoriaServiceImpl;
import com.springboot.backend.services.EstudioServiceImpl;
import com.springboot.backend.services.VideoJuegoServiceImpl;

@Controller
@RequestMapping("/api")
public class VideoJuegoController {

	@Autowired
	private VideoJuegoServiceImpl videoJuegoService;
	
	@Autowired
	private CategoriaServiceImpl categoriaService;
	
	@Autowired
	private EstudioServiceImpl estudiosService;
	
	@GetMapping({"","/","/index"})
	public String inicio(Model model) {
		try {
			List<VideoJuego> listaVideJuegos = this.videoJuegoService.findAllByActivo();
			model.addAttribute("videojuegos", listaVideJuegos);
			return "views/index";
		}catch(Exception ex) {
			model.addAttribute("mensaje", ex.getMessage());
			return "error";
		}
	}
	
	@GetMapping("/quiensoy")
	public String mostrarPerfil(Model model) {
		return "views/quiensoy";
	}
	
	
	@GetMapping("/detalle/{id}")
	public String detalleVideoJuego(Model model,@PathVariable Long id) {
		try {
			VideoJuego videoJuego = this.videoJuegoService.findByIdAndActivo(id);
			model.addAttribute("videojuego", videoJuego);
			return "views/detalle";
		}catch(Exception ex) {
			model.addAttribute("error", ex.getMessage());
			return "error";
		}
	}
	
	@GetMapping("/busqueda")
	public String buscarVideoJuego(@RequestParam(value="titulo",required=false) String titulo,Model model) {
		try {
			List<VideoJuego> listaVideoJuegos = this.videoJuegoService.findByTitle(titulo);
			model.addAttribute("videojuegos", listaVideoJuegos);
			model.addAttribute("titulo", titulo);
			return "views/busqueda";
		}catch(Exception ex) {
			model.addAttribute("error", ex.getMessage());
			return "error";
		}
	}
	
	
	@GetMapping("/crud")
	public String mostrarVideJuegos(Model model) {
		try {
			List<VideoJuego> videojuegos = this.videoJuegoService.findAll();
			model.addAttribute("videojuegos", videojuegos);
			return "views/crud";
		}catch(Exception ex) {
			model.addAttribute("error",ex.getMessage());
			return "error";
		}
	}
	
	
	@GetMapping("/formulario/videojuego/{id}")
	public String mostrarVideJuegos(@PathVariable("id")Long id, Model model) {
		try {
			model.addAttribute("categorias", this.categoriaService.findAll());
			model.addAttribute("estudios", this.estudiosService.findAll());
			
			if(id == 0) {
				model.addAttribute("videojuego", new VideoJuego());
			}else {
				model.addAttribute("videojuego", this.videoJuegoService.findById(id));
			}
			return "views/formulario/videojuego";
		}catch(Exception ex) {
			model.addAttribute("error",ex.getMessage());
			return "error";
		}
	}
	
	
	@PostMapping("/formulario/videojuego/{id}")
	public String guardarVideJuegos(@RequestParam("archivo")MultipartFile archivo,
									@Valid @ModelAttribute("videojuego") VideoJuego videojuego,
									BindingResult results,
									Model model,
									@PathVariable("id")Long id) {
		
		try {
			model.addAttribute("categorias", this.categoriaService.findAll());
			model.addAttribute("estudios", this.estudiosService.findAll());
			
			if(results.hasErrors()) {
				return "views/formulario/videojuego";
			}
			
			/*Path ruta =Paths.get("src/main/resources/static/imagenes");
			String rutaAbsoluta = ruta.toFile().getAbsolutePath();
			Path rutaCompleta = Paths.get(rutaAbsoluta + "//"+ archivo.getOriginalFilename());*/
			String ruta = "src/main/resources/static/imagenes";
			int index = archivo.getOriginalFilename().indexOf(".");
			String extension = "."+archivo.getOriginalFilename().substring(index + 1);
			String nombreFoto = Calendar.getInstance().getTimeInMillis()+extension;
			Path rutaCompleta = id != 0 ? Paths.get(ruta + "//"+videojuego.getImagen()) : Paths.get(ruta+"//"+nombreFoto);
			if(id == 0) {
				
				if(archivo.isEmpty()) {
					model.addAttribute("imagenError", "Se requiere una imagen para el juego.");
					return "views/formulario/videojuego";
				}
				
				if(!this.validarExtension(archivo)) {
					model.addAttribute("imagenError", "La extensión no es válida.");
					return "views/formulario/videojuego";
				}
				
				if(archivo.getSize() >= 15000000) {
					model.addAttribute("imagenError", "El peso de la imagen debe ser menor a 15MB");
					return "views/formulario/videojuego";
				}
				
				Files.write(rutaCompleta, archivo.getBytes());
				videojuego.setImagen(nombreFoto);
				this.videoJuegoService.save(videojuego);
			}else {
				if(!archivo.isEmpty()) {
					if(!this.validarExtension(archivo)) {
						model.addAttribute("imagenError", "La extensión no es válida.");
						return "views/formulario/videojuego";
					}
					
					if(archivo.getSize() > 15000000) {
						model.addAttribute("imagenError", "El peso de la imagen debe ser menor a 15MB");
						return "views/formulario/videojuego";
					}
					
					Files.write(rutaCompleta, archivo.getBytes());
				}
				this.videoJuegoService.update(videojuego, id);
			}
			return "redirect:/api/crud";
		}catch(Exception ex) {
			model.addAttribute("error",ex.getMessage());
			return "error";
		}
	}
	
	
	
	@GetMapping("/eliminar/videojuego/{id}")
	public String eliminarVideJuego(@PathVariable("id")Long id, Model model) {
		try {
			model.addAttribute("videojuego", this.videoJuegoService.findById(id));
			return "views/formulario/eliminar";
		}catch(Exception ex) {
			model.addAttribute("error",ex.getMessage());
			return "error";
		}
	}
	
	
	@PostMapping("/eliminar/videojuego/{id}")
	public String desactivarVideJuego(@PathVariable("id")Long id, Model model) {
		try {
			this.videoJuegoService.deleteById(id);
			return "redirect:/api/crud";
		}catch(Exception ex) {
			model.addAttribute("error",ex.getMessage());
			return "error";
		}
	}
	
	
	public boolean validarExtension(MultipartFile archivo) {
		try {
			ImageIO.read(archivo.getInputStream()).toString();
			return true;
		}catch(Exception ex) {
			return false;
		}
	}
}
