package com.springboot.backend.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Controlador {

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("titulo", "Springboot y thymeleaf");
		return "index";
	}
}
