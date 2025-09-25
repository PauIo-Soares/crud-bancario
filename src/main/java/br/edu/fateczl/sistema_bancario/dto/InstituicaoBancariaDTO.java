package br.edu.fateczl.sistema_bancario.dto;

import lombok.Data;

@Data
public class InstituicaoBancariaDTO {
        private Long codigo;
        private String nome;
        private String cep;
        private String cidade;
}