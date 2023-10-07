package com.biblioteca.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
