package com.biblioteca.perfil;

import com.biblioteca.database.FuncoesSQL;
import com.biblioteca.model.CopiaDoLivroPrototype.CopiaDoLivroModel;
import com.biblioteca.model.LeitorPrototype.LeitorModel;
import com.biblioteca.model.LivroPrototype.LivroModel;

import java.util.ArrayList;

import java.util.List;

public class AtendenteFacade {

	public static void realizarCadastro(LeitorModel leitorModel) {
		AtendenteModel atendenteModel = new AtendenteModel(leitorModel);

		if(atendenteModel.cpfJaCadastrado()) {
			return;
		}
		atendenteModel.cadastrar();
	}

	public static void realizarEmprestimo(EmprestimoModel emprestimoModel) {
		RealizarEmprestimo realizarEmprestimo = new RealizarEmprestimo(emprestimoModel);

		if(realizarEmprestimo.verificarSenha() && realizarEmprestimo.tryToFindCopia()) {
			realizarEmprestimo.alterarSituacao();
		    realizarEmprestimo.salvarEmprestimo();
		}
	}

	public static boolean login(EmprestimoModel emprestimoModel) {
		LoginLeitor loginLeitor = new LoginLeitor(emprestimoModel);

		if(loginLeitor.login()) {
			return true;
		}
		return false;
	}

	public static void devolver(int id) {		
		if(Devolucao.verifcarId(id)) {
			Devolucao.updateCopias(id);
			Devolucao.deletarEmprestimo(id);
		}
	}

	public static List<DevolucaoModel> getLivrosEmprestados(EmprestimoModel emprestimoModel) {
		Devolucao devolucao = new Devolucao(emprestimoModel);
		return devolucao.getDevolucaoModels();
	}

    
	public static List<LivroModel> getLivros() {
		return AtendenteModel.apresentarLivros();
	}

	public static List<CopiaDoLivroModel> getCopiaDoLivroModel() {
        List<CopiaDoLivroModel> copiaDosLivros = FuncoesSQL.getCopias();
		return copiaDosLivros;
	}
}


