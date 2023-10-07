package com.biblioteca.perfil;

import com.biblioteca.model.LeitorPrototype.LeitorModel;

public class CadastroFacade {

	public static void realizarCadastro(LeitorModel leitorModel) {
		AtendenteModel atendenteModel = new AtendenteModel(leitorModel);

		if(atendenteModel.cpfJaCadastrado()) {
			return;
		}

		atendenteModel.cadastrar();
	}
	
}


