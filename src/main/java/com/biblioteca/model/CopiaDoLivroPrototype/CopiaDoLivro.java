package com.biblioteca.model.CopiaDoLivroPrototype;

import com.biblioteca.model.LivroPrototype.Livro;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CopiaDoLivro implements ICopiaDoLivroPrototype {
    private int sequencial;
    private Livro livro;
    private String situacao;
    private Boolean liberacaoEmprestimo;

    @Override
    public CopiaDoLivroModel clone() {
        CopiaDoLivroModel copiaDoLivroModel = new CopiaDoLivroModel(sequencial, livro.getTitulo(), situacao, liberacaoEmprestimo);
        return copiaDoLivroModel;
    }
}
