package com.biblioteca.model.LeitorPrototype;

import com.biblioteca.domain.ReservaSituacao;
import com.biblioteca.model.CopiaDoLivroPrototype.CopiaDoLivro;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reserva {
    private int periodo;
    private ReservaSituacao situacao;   
    private CopiaDoLivro copiaDoLivro;
}
