package com.biblioteca.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.biblioteca.model.CopiaDoLivro;
import com.biblioteca.model.LibEmployee;
import com.biblioteca.model.LivroPrototype.Livro;

public class FuncoesSQL {


	private Statement statement;

	public static void main(String[] args) {
		
	}

	public FuncoesSQL() {
		Connection connection = DataBase.getConnection();
        try {
		    this.statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}




	//get valores
	public LibEmployee getEmployee(String libEmployeeEmail) {
		LibEmployee libEmployee = new LibEmployee();

		try {
			ResultSet resultSet = statement.executeQuery(
			String.format("SELECT Email, Senha, Nome, Cargo FROM AB WHERE Email = '%s'", libEmployeeEmail));

		    libEmployee.setEmail(resultSet.getString("Email"));
			libEmployee.setSenha(resultSet.getString("Senha"));
			libEmployee.setNome(resultSet.getString("Nome"));
			libEmployee.setCargo(resultSet.getString("Cargo"));
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return libEmployee;
	}

	//terminar o get livro
	public CopiaDoLivro getCopiaDoLivro(int sequencia) {
		String sqlString = String.format(
			"SELECT Sequencia, Titulo, Situacao Liberacao_para_emprestimo FROM Copias WHERE Sequencia = '%d'", sequencia);
        
		CopiaDoLivro copiaDoLivro = new CopiaDoLivro();
        Livro livro;

		try {
			ResultSet resultSet = statement.executeQuery(sqlString);

			livro = getLivro(resultSet.getString("Titulo"));
			copiaDoLivro.setLivro(livro);
			
			copiaDoLivro.setSequencial(resultSet.getInt("Sequencia"));
			copiaDoLivro.setSituacao(resultSet.getString("Situcao"));
			copiaDoLivro.setLiberacaoEmprestimo(resultSet.getString("Liberacao_para_emprestimo"));

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return copiaDoLivro;
	}

	public Livro getLivro(String titulo) {
		String sqlString = String.format(
			"SELECT Titulo, Email, Autor, Ano, isbn, Editora, Tipo FROM Livro WHERE Titulo = '%s'", 
			titulo
		);
		
		Livro livro = new Livro();

	    try {
			ResultSet resultSet = statement.executeQuery(sqlString);

			livro.setTitulo(resultSet.getString("Titulo"));
			livro.setAutor(resultSet.getString("Autor"));
			livro.setAno(resultSet.getInt("Ano"));
			livro.setIsbn(resultSet.getString("ibsn"));
			livro.setEditora(resultSet.getString("Editora"));
			livro.setTipo(resultSet.getString("Tipo"));

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return livro;
	}



	
}
