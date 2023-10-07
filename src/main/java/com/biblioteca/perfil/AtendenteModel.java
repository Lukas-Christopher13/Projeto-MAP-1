package com.biblioteca.perfil;

import com.biblioteca.database.FuncoesSQL;
import com.biblioteca.model.LeitorPrototype.LeitorModel;

public class AtendenteModel {

	private LeitorModel leitorModel;

	public AtendenteModel(LeitorModel leitorModel) {
		this.leitorModel = leitorModel;
	}

	public boolean cpfJaCadastrado() {
		String cpfString = Integer.toString(leitorModel.getCpf());
		return FuncoesSQL.tryToFindItem("Leitor", "Cpf", cpfString);
	}

	public void cadastrar() {
		FuncoesSQL.cadastrarLeitor(leitorModel);
	}
	
}
