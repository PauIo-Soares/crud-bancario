package br.edu.fateczl.sistema_bancario.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgenciaDTO {
    private Long codigo;
    private String nome;
    private String cep;
    private String cidade;
    private Long codigoInstituicao;
}