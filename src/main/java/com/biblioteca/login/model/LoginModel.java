package com.biblioteca.login.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.biblioteca.database.DataBase;
import com.biblioteca.model.LibEmployee;

public class LoginModel {

	private LibEmployee libEmployee;
	private Statement statement;

	public LoginModel(LibEmployee libEmployee) {
		this.libEmployee = libEmployee;

		Connection connection = DataBase.getConnection();
        try {
		    this.statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean hasUser() {
		List<String> emails = tryToExecuteReadFild("Email");
		return emails.contains(libEmployee.getEmail());
	}

	public boolean passwordEquals() {
		List<String> senhas = tryToExecuteReadFild("Senha");
		return senhas.contains(libEmployee.getSenha());
	}

	public LibEmployee getEmployee() {
		return tryToGetEmployee();
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

	private List<String> tryToExecuteReadFild(String field) {
		List<String> emails = new ArrayList<>();

		try {
			ResultSet resultSet = statement.executeQuery("SELECT * FROM AB");
			//read all lines from ab table
			while(resultSet.next()) {
				emails.add(resultSet.getString(field));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emails;
	}

	public boolean isAtendente() {
		return false;
	}

	
}
