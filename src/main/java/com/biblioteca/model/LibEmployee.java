package com.biblioteca.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LibEmployee {	
	private String nome;

    @NotNull
	@NotEmpty
	private String email;

	@NotNull
	@Size(min=2, max=16)
	private String senha;

	private String cargo;
}
