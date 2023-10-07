package com.biblioteca.login.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.biblioteca.database.DataBase;

public class Execute {

	private static Statement openData() throws SQLException {
	    Connection connection = DataBase.getConnection();
		Statement statement = connection.createStatement();

        
  
		statement.close();

		return statement;
	}

	
}
