package com.biblioteca.perfil.BibliotecariasModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.biblioteca.database.DataBase;
import com.biblioteca.model.LivroBuilder.Livro;

public class BibliotecariaModel {

	public void registrarLivro(Livro livro) {
		tryToRegistrarLivro(livro);
	}

	public boolean tryToFindBook(String nome) {
        String sqlSelectName = String.format("SELECT Titulo FROM Livro WHERE Titulo='%s'", nome);
		Connection connection = DataBase.getConnection();
		
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlSelectName);

			if(resultSet.next()) {
				String titulo = resultSet.getString("Titulo");
				return titulo.equals(nome);
			}

			return false;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	private void tryToRegistrarLivro(Livro livro) {
		String email = "yazucanegadacreft@gmail.com";
        String sqlLivroInsert = String.format(
			"INSERT INTO Livro values('%s', '%s', '%s', '%s', '%s', '%s', '%s')" ,
			livro.getTitulo(),
			email,
			livro.getAutor(),
			livro.getAutor(),
			livro.getIsbn(),
			livro.getEditora(),
			livro.getTipo()
		);

		Connection connection = DataBase.getConnection();
		try {
            Statement statement = connection.createStatement();
			statement.executeUpdate(sqlLivroInsert);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void gerarCopias(int num) {

	}

	private void tryToGerarCopias(int num) {

	}
}
