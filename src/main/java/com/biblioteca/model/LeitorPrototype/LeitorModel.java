package com.biblioteca.model.LeitorPrototype;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeitorModel implements ILivroPrototype{
	private int cpf;
    private String nome;
	private String emialDoBibliotecario;
	private String senha;
    private String tipo;

	@Override
	public Leitor clone() {
		Leitor leitor = new Leitor();

		leitor.setNome(nome);
		leitor.setTipo(tipo);

		return leitor;
	}
}


