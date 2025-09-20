package br.edu.fateczl.sistema_bancario.model;

import lombok.Data;

@Data
public class InstituicaoBancaria {
    private Long Codigo;
    private String nome;
    private String cep;
    private String cidade;
}
