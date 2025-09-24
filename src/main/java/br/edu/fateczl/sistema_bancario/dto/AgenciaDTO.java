package br.edu.fateczl.sistema_bancario.dto;

import lombok.Data;

@Data
public class AgenciaDTO {
    private String nome;
    private String cep;
    private String cidade;
    private Long instituicaoCodigo;
}