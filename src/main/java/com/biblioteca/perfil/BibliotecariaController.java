package com.biblioteca.perfil;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.biblioteca.model.LivroBuilder.Livro;
import com.biblioteca.perfil.BibliotecariasModel.BibliotecariaModel;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/bibliotecaria")
public class BibliotecariaController {
	
	@GetMapping
	public String bibliotecariaView(Model model) {
		model.addAttribute("livro", new Livro());
		return "perfil/bibliotecaria";
	}

	//falta corrigir os campos
	@PostMapping
    public String cadastrarLivro(@ModelAttribute @Valid Livro livro, Model model, BindingResult bindingResult) {
		model.addAttribute("livro", livro);
		
        BibliotecariaModel bibliotecariaModel = new BibliotecariaModel();
        
		if(bibliotecariaModel.tryToFindBook(livro.getTitulo())) {
			return "perfil/bibliotecaria";
		}
         
		bibliotecariaModel.registrarLivro(livro);

		return "perfil/bibliotecaria";
	}

	public String deletarLivro() {
		return null;
	}

	public String adicionarCopias() {
		return null;
	}

	public String deletarCopias() {
		return null;
	}
}
