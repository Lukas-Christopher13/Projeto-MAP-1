package com.biblioteca.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.biblioteca.model.LibEmployee;

public class FuncoesSQL {

	private Statement statement;

	public FuncoesSQL() {
		Connection connection = DataBase.getConnection();
        try {
		    this.statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private LibEmployee tryToGetEmployee() {
		LibEmployee libEmployee = new LibEmployee();

		try {
			ResultSet resultSet = statement.executeQuery(
			String.format("SELECT Email, Senha, Nome, Cargo FROM AB WHERE Email = '%s'", this.libEmployee.getEmail()));

		    libEmployee.setEmail(resultSet.getString("Email"));
			libEmployee.setSenha(resultSet.getString("Senha"));
			libEmployee.setNome(resultSet.getString("Nome"));
			libEmployee.setCargo(resultSet.getString("Cargo"));
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return libEmployee;
	}

	
}
