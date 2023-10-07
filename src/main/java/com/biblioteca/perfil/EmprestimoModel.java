package com.biblioteca.perfil;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmprestimoModel {
	private int sequencia;
	private int cpf;
	private String senha;
}
