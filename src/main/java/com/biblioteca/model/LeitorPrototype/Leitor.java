package com.biblioteca.model.LeitorPrototype;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Leitor implements ILivroPrototype{
    private String nome;
    private String tipo;
    private List<Reserva> reservas;

    public LeitorModel clone() {
        LeitorModel leitorModel = new LeitorModel();

        leitorModel.setNome(nome);
        leitorModel.setTipo(tipo);

        return leitorModel;
    }
}
