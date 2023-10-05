package com.biblioteca.perfil;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/atendente")
public class AtendenteController {

	@GetMapping
	public String atendenteView(Model model) {
		model.addAttribute("libEmployee", model.asMap().get("libEmployee"));
		return "perfil/atendente";
	}


	
}
