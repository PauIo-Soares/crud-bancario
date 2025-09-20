package br.edu.fateczl.sistema_bancario.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Cliente {
    private String cpf;
    private String nome;
    private LocalDate dataPrimeiraConta;
    private String senha;
}
