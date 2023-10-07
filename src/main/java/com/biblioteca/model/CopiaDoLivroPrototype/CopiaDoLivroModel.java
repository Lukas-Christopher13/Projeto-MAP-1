package com.biblioteca.model.CopiaDoLivroPrototype;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CopiaDoLivroModel implements ICopiaDoLivroPrototype{
    private int sequencia;
    private String livro;
    private String situacao;
    private Boolean liberacaoEmprestimo;

	@Override
	public CopiaDoLivro clone() {
		CopiaDoLivro copiaDoLivro = new CopiaDoLivro(sequencia, null, situacao, liberacaoEmprestimo);
		return copiaDoLivro;
	}
}
