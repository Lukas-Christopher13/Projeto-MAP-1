package com.biblioteca.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LinhaDoEmprestimo {
    private LocalDateTime dataPrevistaDevolucao;
    private LocalDateTime dataRealDevolucao;
}
