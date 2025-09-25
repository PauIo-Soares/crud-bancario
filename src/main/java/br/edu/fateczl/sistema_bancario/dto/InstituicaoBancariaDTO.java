package br.edu.fateczl.sistema_bancario.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InstituicaoBancariaDTO {
        private Long codigo;
        private String nome;
        private String cep;
        private String cidade;
}