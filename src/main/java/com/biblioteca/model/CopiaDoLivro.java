package com.biblioteca.model;

import com.biblioteca.model.LivroPrototype.Livro;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CopiaDoLivro {
    private int sequencial;
    private Livro livro;
    private String situacao;
    private String liberacaoEmprestimo;
}
