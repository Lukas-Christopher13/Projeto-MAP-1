package com.biblioteca.perfil;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.biblioteca.model.LibEmployee;
import com.biblioteca.model.LivroPrototype.Livro;
import com.biblioteca.perfil.BibliotecariasModel.BibliotecariaModel;
import com.biblioteca.model.LivroPrototype.LivroModel;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/bibliotecaria")
public class BibliotecariaController {

	private List<Livro> livros;
	private LibEmployee bibliotecaria;
	private BibliotecariaModel bibliotecariaModel = null;

	@GetMapping
	public String bibliotecariaView(Model model) {

		if(bibliotecaria == null) {
			this.bibliotecaria = (LibEmployee) model.asMap().get("libEmployee");
		}

        this.bibliotecariaModel = new BibliotecariaModel(null, bibliotecaria);
        this.livros = bibliotecariaModel.apresentarLivros();

		model.addAttribute("bibliotecaria", this.bibliotecaria);
		model.addAttribute("livros", livros);
		model.addAttribute("livroModel", new LivroModel());
	
		return "perfil/bibliotecaria";
	}

	//falta corrigir os campos
	@PostMapping
    public String cadastrarLivro(@ModelAttribute @Valid LivroModel livroModel, Model model, BindingResult bindingResult) {
		model.addAttribute("livroModel", livroModel);
		
		//Uso de Portotype
        Livro livro = livroModel.clone();

        BibliotecariaModel bibliotecariaModel = new BibliotecariaModel(livro, bibliotecaria);
        
		if(bibliotecariaModel.tryToFindBook(livro.getTitulo())) {
			return "redirect:bibliotecaria";
		}
        
		bibliotecariaModel.registrarLivro(livroModel.getQuantidade());

		return "redirect:bibliotecaria";
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
