package com.biblioteca.model;

import com.biblioteca.domain.LiberacaoEmprestimo;
import com.biblioteca.model.LivroBuilder.Livro;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CopiaDoLivro {
    private int sequencial;
    private Livro livro;
    private CopiaDoLivroSituacao situacao;
    private LiberacaoEmprestimo liberacaoEmprestimo;
}
