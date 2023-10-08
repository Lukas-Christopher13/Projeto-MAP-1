package com.biblioteca.perfil;

import java.util.List;

import com.biblioteca.database.DataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Devolucao {

	private EmprestimoModel emprestimoModel;

	public Devolucao(EmprestimoModel emprestimoModel) {
		this.emprestimoModel = emprestimoModel;
	}

	public List<DevolucaoModel> getDevolucaoModels() {
        ArrayList<DevolucaoModel> leitor_realiza_emprestimo = new ArrayList<>();
        String sqlString = String.format(
			"SELECT * FROM Leitor_Realiza_Emprestimo WHERE Cpf = %d", this.emprestimoModel.getCpf());

		try {
			Connection connection = DataBase.getConnection();
			Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlString);

			while (resultSet.next()) {
				DevolucaoModel devolucaoModel = new DevolucaoModel();

				devolucaoModel.setCpf(resultSet.getInt("Cpf"));
				devolucaoModel.setId_copia(resultSet.getInt("Emprestimo_id"));

				leitor_realiza_emprestimo.add(devolucaoModel);
			}

			statement.close();
			resultSet.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return leitor_realiza_emprestimo;
	}

	public static boolean verifcarId(int id) {
        int valor = 0;

		String sqlString = String.format(
			"SELECT * FROM Leitor_Realiza_Emprestimo WHERE Emprestimo_id = %d", id);
		
        try {
			Connection connection = DataBase.getConnection();
		    Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlString);

			valor = resultSet.getInt("Emprestimo_id");

		    statement.close();
			resultSet.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		if(valor == id) {
			return true;
		}
		return false;
	}

	public static void updateCopias(int id) {
		String sqlString = String.format(
			"""
			UPDATE Copias SET
			Situacao = 'LIBERADO', 
			Liberacao_para_emprestimo = 'TRUE'
			WHERE Sequencia = %d
			""", id);

		try {
			Connection connection = DataBase.getConnection();
		    Statement statement = connection.createStatement();
            statement.execute(sqlString);
 
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void deletarEmprestimo(int id) {
		String sqlString = String.format(
			"DELETE FROM Leitor_Realiza_Emprestimo WHERE Emprestimo_id = %d", 
			id
		);
		
		try {
			Connection connection = DataBase.getConnection();
		    Statement statement = connection.createStatement();

			statement.execute(sqlString);

			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}        	
	}
}
