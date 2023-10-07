package com.biblioteca.login.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.biblioteca.database.DataBase;
import com.biblioteca.model.LibEmployee;

public class GetModels {

	private Connection connection;
	private	Statement statement;
    private ResultSet resultSet; 

	public GetModels() {
		this.connection = DataBase.getConnection();

	}

	public LibEmployee tryToGetEmployee(String email) {

	
		String sqlString = String.format("SELECT Email, Senha, Nome, Cargo FROM AB WHERE Email = '%s'", email);
		LibEmployee libEmployee = new LibEmployee();

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sqlString);

		    libEmployee.setEmail(resultSet.getString("Email"));
			libEmployee.setSenha(resultSet.getString("Senha"));
			libEmployee.setNome(resultSet.getString("Nome"));
			libEmployee.setCargo(resultSet.getString("Cargo"));

			statement.close();
		    resultSet.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return libEmployee;
	}

}
