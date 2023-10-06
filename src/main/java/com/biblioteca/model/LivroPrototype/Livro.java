package com.biblioteca.model.LivroPrototype;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Livro implements ILivroPrototype{
    private String titulo;
    private String autor;
    private int ano;
    private String isbn;
    private String editora;
    private String tipo;

    public Livro clone(){
        return new Livro(titulo, autor, ano, isbn, editora, tipo);
    }
}
