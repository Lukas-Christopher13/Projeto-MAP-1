package com.biblioteca.perfil;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.biblioteca.model.LibEmployee;
import com.biblioteca.model.LeitorPrototype.LeitorModel;

@Controller
@RequestMapping("/atendente")
public class AtendenteController {

	private LibEmployee libEmployee;

	@GetMapping
	public String atendenteView(Model model) {
		if(this.libEmployee == null) {
			this.libEmployee = (LibEmployee) model.asMap().get("libEmployee");
		}

		return "perfil/atendente";
	}

	@GetMapping("/cadastrar")
	public String cadastrarLeitorView(Model model) {
		model.addAttribute("leitorModel", new LeitorModel());
		return "perfil/cadastrar";
	}

	@PostMapping("/cadastrar")
	public String cadastrarLeitor(@ModelAttribute LeitorModel leitorModel, Model model) {
		model.addAttribute("leitorModel", leitorModel);

		leitorModel.setEmialDoBibliotecario(libEmployee.getEmail());

		CadastroFacade.realizarCadastro(leitorModel);

		return "redirect:cadastrar";
	}


	
}
