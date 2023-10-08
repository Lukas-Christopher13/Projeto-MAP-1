package com.biblioteca.perfil;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.biblioteca.database.DataBase;
import com.biblioteca.model.LeitorPrototype.LeitorModel;

public class LoginLeitor {

	private EmprestimoModel emprestimoModel;

	public LoginLeitor(EmprestimoModel emprestimoModel) {
		this.emprestimoModel = emprestimoModel;
	}

	public boolean login() {
        String senha = emprestimoModel.getSenha();

		LeitorModel lietLeitorModel = getLeitor();

		if(lietLeitorModel.getCpf() == 0) {
			return false;
		}
		if(!senha.equals(lietLeitorModel.getSenha())){
			return false;
		}
		return true;
	}

	public LeitorModel getLeitor() {
        LeitorModel leitorModel = new LeitorModel();

		String sqlString = String.format(
			"SELECT * FROM Leitor WHERE Cpf = %d", emprestimoModel.getCpf());

	    try {
			Connection connection = DataBase.getConnection();
			Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlString);

			leitorModel.setCpf(resultSet.getInt("Cpf"));
			leitorModel.setEmialDoBibliotecario(resultSet.getString("Email"));
			leitorModel.setNome(resultSet.getString("Nome"));
			leitorModel.setSenha(resultSet.getString("Senha"));
			leitorModel.setTipo(resultSet.getString("Tipo"));

			statement.close();
			resultSet.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return leitorModel;
	}
}
