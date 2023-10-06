package com.biblioteca.model.LivroPrototype;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LivroModel implements ILivroPrototype {
    private String titulo;
    private String autor;
    private int ano;
    private String isbn;
    private String editora;
    private String tipo;
	private int quantidade;

    @Override
	public Livro clone() {
        Livro cloneLivroModel = new Livro(
            titulo, autor, ano, isbn, editora, tipo
        );
        return cloneLivroModel;
    }

}
