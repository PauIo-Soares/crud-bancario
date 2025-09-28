package br.edu.fateczl.sistema_bancario.dto;

import lombok.Data;

@Data
public class AlterarSenhaDTO {
    private LoginDTO login;
    private String novaSenha;
}