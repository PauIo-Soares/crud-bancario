package br.edu.fateczl.sistema_bancario.dto;

import br.edu.fateczl.sistema_bancario.enums.TipoConta;
import lombok.Data;

@Data
public class CriarContaDTO {
    private String cpf;
    private String cpfConjunto;
    private String nomeConjunto;
    private String senhaConjunta;
    private TipoConta tipoConta;
    private String codigoAgencia;
}