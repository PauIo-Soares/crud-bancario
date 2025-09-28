package br.edu.fateczl.sistema_bancario.dto;

import br.edu.fateczl.sistema_bancario.enums.TipoConta;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClienteNovoDTO {
    private String cpf;
    private String nome;
    private String senha;
    private TipoConta tipoConta;
    private String codigoAgencia;
}