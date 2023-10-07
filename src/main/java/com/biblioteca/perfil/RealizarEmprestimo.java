package com.biblioteca.perfil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Random;

import com.biblioteca.database.DataBase;
import com.biblioteca.database.FuncoesSQL;
import com.biblioteca.model.CopiaDoLivroPrototype.CopiaDoLivroModel;

public class RealizarEmprestimo {

	private EmprestimoModel emprestimoModel;

	public RealizarEmprestimo(EmprestimoModel emprestimoModel) {
		this.emprestimoModel = emprestimoModel;
	}

	public boolean verificarSenha() {
        String cpf = Integer.toString(emprestimoModel.getCpf());

		if(FuncoesSQL.tryToFindItem("Leitor", "Cpf", cpf) && FuncoesSQL.tryToFindItem("Leitor", "Senha", emprestimoModel.getSenha())
		) {
			return true;
		}
		return false;
	}

	public void salvarEmprestimo() {
		Random random = new Random();

        Integer readomNumber = random.nextInt(10, 20);

		LocalDate hoje = LocalDate.now();
		LocalDate dataDaDevolucao = hoje.plusDays(15);
		LocalDate dataPrevista = hoje.plusDays(readomNumber);

        String sqlString = String.format(
			"""
				INSERT INTO Emprestimo(Situacao, Data, Data_da_devolucao, Data_prevista_para_devolucao)
				VALUES ('EMPRESTADO', '%s', '%s', '%s' )
			""",
			hoje.toString(),
			dataDaDevolucao.toString(),
			dataPrevista.toString()
		);

		try {
			Connection connection = DataBase.getConnection();
			Statement statement = connection.createStatement();
			statement.executeUpdate(sqlString);

            salvarLeitorRealizaEmprestimo();

			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void salvarLeitorRealizaEmprestimo() {
		String sqlString = String.format("""
			INSERT INTO Leitor_Realiza_Emprestimo (Cpf, Emprestimo_id)
			VALUES('%s', '%s')
			""",
			emprestimoModel.getCpf(),
			getSqlSequence("Emprestimo" )
		);

		try {
			Connection connection = DataBase.getConnection();
			Statement statement = connection.createStatement();

			statement.executeUpdate(sqlString);

			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private int getSqlSequence(String table) {
		int nextNum = 0;
		
		String sqlString = String.format("SELECT seq FROM sqlite_sequence WHERE name = '%s'" , table);

		try {
			Connection connection = DataBase.getConnection();
			Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlString);

			nextNum = resultSet.getInt("seq");

			resultSet.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nextNum;
	}

	public void alterarSituacao() {
		String sqlString = String.format(
			"""
			UPDATE Copias 
			SET Situacao = 'EMPRESTADO',
			Liberacao_para_emprestimo = 'FALSE'
		    WHERE Sequencia = %d
			""" ,
			this.emprestimoModel.getSequencia()
		);

		try {
			Connection connection = DataBase.getConnection();
			Statement statement = connection.createStatement();
			statement.executeUpdate(sqlString);

			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean tryToFindCopia() {
		CopiaDoLivroModel copiaDoLivroModel = FuncoesSQL.getCopia(emprestimoModel.getSequencia());
		String liberadoParaEmprestimo = copiaDoLivroModel.getSituacao();

        if(liberadoParaEmprestimo.equals("LIBERADO")) {
			return true;
		}
		return false;
	}

}



	
