package br.edu.fateczl.sistema_bancario.dto;

import lombok.Data;

@Data
public class LoginDTO {
    private String cpf;
    private String senha;
}