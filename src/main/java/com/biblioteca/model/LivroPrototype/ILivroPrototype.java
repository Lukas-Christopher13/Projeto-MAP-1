package com.biblioteca.model.LivroPrototype;

//Para relizar a comunicação com a view é necessairo mais o campo "quantidade" para criar copias
//do livro. Pensando nisso criei um classe 'LivroModel' responsavel por interagir com o front end
//e implementei um "Prototype" nela para facilitar a criação do objeto 'Livor', clonando um 'LivroModel'
//para um "Livro".
public interface ILivroPrototype {
	public ILivroPrototype clone();
}
