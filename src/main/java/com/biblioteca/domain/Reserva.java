package com.biblioteca.domain;

import com.biblioteca.model.CopiaDoLivro;

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
