package com.biblioteca.perfil;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.biblioteca.domain.Emprestimo;
import com.biblioteca.model.LibEmployee;
import com.biblioteca.model.CopiaDoLivroPrototype.CopiaDoLivroModel;
import com.biblioteca.model.LeitorPrototype.LeitorModel;
import com.biblioteca.model.LivroPrototype.LivroModel;

@Controller
@RequestMapping("/atendente")
public class AtendenteController {

	private LibEmployee libEmployee;
	private EmprestimoModel emprestimoModel;

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

		AtendenteFacade.realizarCadastro(leitorModel);

		return "redirect:cadastrar";
	}

	@GetMapping("/emprestimo") 
	public String emprestarView(Model model) {
        List<LivroModel> livrosModel = AtendenteFacade.getLivros();
		List<CopiaDoLivroModel> copiaDoLivroModel = AtendenteFacade.getCopiaDoLivroModel();

		model.addAttribute("livros", livrosModel);
		model.addAttribute("copias", copiaDoLivroModel);
		model.addAttribute("emprestimo", new EmprestimoModel());

		return "perfil/emprestimo";
	}

	@PostMapping("/emprestimo")
	public String emprestar(@ModelAttribute EmprestimoModel emprestimoModel, Model model) {
		model.addAttribute("emprestimo", emprestimoModel);

		AtendenteFacade.realizarEmprestimo(emprestimoModel);

		return "redirect:emprestimo";
	}

	@GetMapping("/devolucao/login")
	public String loginDevolucaoVeiw(Model model) {
		model.addAttribute("devolucao", new EmprestimoModel());

		return "perfil/login";
	}

	@PostMapping("/devolucao/login")
	public String loginDevolucao(@ModelAttribute EmprestimoModel emprestimoModel, Model model, RedirectAttributes redirectAttributes) {
		model.addAttribute("devolucao", emprestimoModel);


		if(AtendenteFacade.login(emprestimoModel)){
			redirectAttributes.addFlashAttribute("devolucao", emprestimoModel);
			return "redirect:devolver";
		}
		return "redirect:login";
	}

	@GetMapping("/devolucao/devolver")
	private String devolucaoView(Model model) {

        if(emprestimoModel == null) {
			emprestimoModel = (EmprestimoModel) model.asMap().get("devolucao");
		}

        List<DevolucaoModel> devolucaoModels = AtendenteFacade.getLivrosEmprestados(emprestimoModel);

		model.addAttribute("devolucao", new DevolucaoModel());
		model.addAttribute("emprestimos", devolucaoModels);

		return "perfil/devolver";
	}

	@PostMapping("/devolucao/devolver")
	private String devolucao(@ModelAttribute DevolucaoModel devolucaoModel, Model model) {
		model.addAttribute("devolucao", devolucaoModel);


        AtendenteFacade.devolver(devolucaoModel.getId_copia());

		return "redirect:devolver";
	}
}
