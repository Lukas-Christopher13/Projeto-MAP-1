package com.biblioteca.Projeto1.domain;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Emprestimo {
    private LocalDateTime date;
    private EmprestimoSituacao situacao;
    private List<LinhaDoEmprestimo> linhasDoEmprestimo;
}
