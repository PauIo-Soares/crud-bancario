package br.edu.fateczl.sistema_bancario.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ClienteDTO {
    private String cpf;
    private String nome;
    private LocalDate dataPrimeiraConta;
}