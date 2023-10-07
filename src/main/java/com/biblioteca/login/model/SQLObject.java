package com.biblioteca.login.model;

import java.sql.Statement;

public interface SQLObject {
	public Statement execute(Statement statement);
}
