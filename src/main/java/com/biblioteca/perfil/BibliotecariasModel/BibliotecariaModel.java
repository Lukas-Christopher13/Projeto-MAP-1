package com.biblioteca.perfil.BibliotecariasModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.List;
import java.util.ArrayList;

import com.biblioteca.database.DataBase;
import com.biblioteca.model.LibEmployee;
import com.biblioteca.model.LivroPrototype.Livro;

public class BibliotecariaModel {

    private Connection connection; 
	private Statement statement;
	private Livro livro;
	private LibEmployee bibliotecaria;

	public BibliotecariaModel(Livro livro, LibEmployee bibliotecaria) {
	    this.connection = DataBase.getConnection();
        this.livro = livro;
		this.bibliotecaria = bibliotecaria;
	}

	public void registrarLivro(int numeroDeCopias) {
		tryToRegistrarLivro(numeroDeCopias);
	}

	//colocar numero de copias no html
	private void tryToRegistrarLivro(int numeroDeCopias) {
        String sqlLivroInsert = String.format(
			"INSERT INTO Livro values('%s', '%s', '%s', '%s', '%s', '%s', '%s')" ,
			livro.getTitulo(),
			bibliotecaria.getEmail(),
			livro.getAutor(),
			livro.getAutor(),
			livro.getIsbn(),
			livro.getEditora(),
			livro.getTipo()
		);

		try {
			this.statement = connection.createStatement();
			statement.executeUpdate(sqlLivroInsert);

            gerarCopias(numeroDeCopias);

			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void gerarCopias(int numeroDeCopias) {
		String sqlString = String.format(
			"INSERT INTO Copias (Titulo, Situacao, Liberacao_para_emprestimo) VALUES ('%s', 'LIBERADO', 'TRUE')", 
			livro.getTitulo()
		);

		try {
			this.statement = connection.createStatement();

            for (int i = 0; i < numeroDeCopias; i++) {
				statement.executeUpdate(sqlString);
			}

			statement.close();

		} catch (SQLException e) {
			System.err.println("Erro em 'GerarCopies'");
			e.printStackTrace();
		}
	}

	public List<Livro> apresentarLivros() {
		String sqlString = "SELECT * FROM Livro";
		List<Livro> livros = new ArrayList<>();

		try {
			this.statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlString);

			while(resultSet.next()) {
				Livro livro = new Livro();

				livro.setTitulo(resultSet.getString("Titulo"));
				livro.setAutor(resultSet.getString("Autor"));
				livro.setAno(resultSet.getInt("Ano"));
				livro.setIsbn(resultSet.getString("isbn"));
				livro.setEditora(resultSet.getString("Editora"));
				livro.setTipo(resultSet.getString("Tipo"));

				livros.add(livro);
			}

			statement.close();
			resultSet.close();

		} catch (SQLException e) {
            System.err.println("Erro ao apresentar os livros");
			e.printStackTrace();
		}
          
        return livros;
	}


    //tornar uma função
	public boolean tryToFindBook(String nome) {
        String sqlSelectName = String.format("SELECT Titulo FROM Livro WHERE Titulo='%s'", nome);
		
		try {
			this.statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlSelectName);

			if(resultSet.next()) {
				String titulo = resultSet.getString("Titulo");
				statement.close();
			    resultSet.close();
				return titulo.equals(nome);
			}

			statement.close();
			resultSet.close();

			return false;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}


	private void tryToGerarCopias(int num) {

	}
}
