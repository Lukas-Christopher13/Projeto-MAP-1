package com.biblioteca.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.biblioteca.model.CopiaDoLivroPrototype.CopiaDoLivroModel;
import com.biblioteca.model.LeitorPrototype.LeitorModel;

public class FuncoesSQL {

	public static boolean tryToFindItem(String tabela, String campo, String valor) {
        String sqlSelectName = String.format(
			"SELECT %s FROM %s WHERE %s=%s", 
		    campo, tabela, campo, valor
		);
		
		try {
			Connection connection = DataBase.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlSelectName);

			if(resultSet.next()) {
				String titulo = resultSet.getString(campo);

				statement.close();
			    resultSet.close();

				return titulo.equals(valor);
			}

			statement.close();
			resultSet.close();

			return false;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static void cadastrarLeitor(LeitorModel leitorModel) {
		String sqlString = String.format(
			"INSERT INTO Leitor VALUES ('%s', '%s', '%s', '%s', '%s')", 
			leitorModel.getCpf(),
			leitorModel.getEmialDoBibliotecario(),
			leitorModel.getSenha(),
			leitorModel.getNome(),
			leitorModel.getTipo()
		);

		Connection connection = DataBase.getConnection();
		
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(sqlString);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static List<CopiaDoLivroModel> getCopias() {
		ArrayList<CopiaDoLivroModel> copias = new ArrayList<>();
        
		String sqlString = "SELECT * FROM  Copias";

		try {
			Connection connection = DataBase.getConnection();
			Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlString);

			while(resultSet.next()) {
				CopiaDoLivroModel copiaDoLivro = new CopiaDoLivroModel();

				copiaDoLivro.setSequencia(resultSet.getInt("Sequencia"));
				copiaDoLivro.setLivro(resultSet.getString("Titulo"));
				copiaDoLivro.setSituacao(resultSet.getString("Situacao"));
				copiaDoLivro.setLiberacaoEmprestimo(resultSet.getBoolean("Liberacao_para_emprestimo"));

				copias.add(copiaDoLivro);
			}

			statement.close();
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return copias;
	}

	public static List<String> tryToExecuteReadFild(String tabela,  String field) {
		List<String> itens = new ArrayList<>();
		String sqlString = String.format("SELECT * FROM %s", tabela);

		try {
			Connection connection = DataBase.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlString);
			//read all lines from ab table
			while(resultSet.next()) {
				itens.add(resultSet.getString(field));
			}
			resultSet.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return itens;
	}

	public static CopiaDoLivroModel getCopia(int sequencia) {
		CopiaDoLivroModel copiaDoLivro = new CopiaDoLivroModel();
		String sqlString = String.format("""
			SELECT * FROM Copias WHERE Sequencia = %d
			""", sequencia);

		try {
			Connection connection = DataBase.getConnection();
		    Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlString);

            copiaDoLivro.setSequencia(resultSet.getInt("Sequencia"));
			copiaDoLivro.setLivro(resultSet.getString("Titulo"));
			copiaDoLivro.setSituacao(resultSet.getString("Situacao"));
			copiaDoLivro.setLiberacaoEmprestimo(resultSet.getBoolean("Liberacao_para_emprestimo"));

			resultSet.close();
			statement.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}  

		return copiaDoLivro;
	}
}
