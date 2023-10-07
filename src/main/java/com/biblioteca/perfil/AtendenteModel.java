package com.biblioteca.perfil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.biblioteca.database.DataBase;
import com.biblioteca.database.FuncoesSQL;
import com.biblioteca.model.LeitorPrototype.LeitorModel;
import com.biblioteca.model.LivroPrototype.LivroModel;

public class AtendenteModel {

	private LeitorModel leitorModel;

	public AtendenteModel(LeitorModel leitorModel) {
		this.leitorModel = leitorModel;
	}

	public boolean cpfJaCadastrado() {
		String cpfString = Integer.toString(leitorModel.getCpf());
		return FuncoesSQL.tryToFindItem("Leitor", "Cpf", cpfString);
	}

	public void cadastrar() {
		FuncoesSQL.cadastrarLeitor(leitorModel);
	}

	public static List<LivroModel> apresentarLivros() {
		String sqlString = "SELECT * FROM Livro";
		List<LivroModel> livros = new ArrayList<>();

		try {
			Connection connection = DataBase.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlString);

			while(resultSet.next()) {
				LivroModel livro = new LivroModel();

				livro.setTitulo(resultSet.getString("Titulo"));
				livro.setEmail(resultSet.getString("Email"));
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
}
	
