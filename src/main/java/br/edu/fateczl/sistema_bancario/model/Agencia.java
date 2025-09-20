package br.edu.fateczl.sistema_bancario.model;

import lombok.Data;

@Data
public class Agencia {
    private Long codigo;
    private String nome;
    private String cep;
    private String cidade;
}
