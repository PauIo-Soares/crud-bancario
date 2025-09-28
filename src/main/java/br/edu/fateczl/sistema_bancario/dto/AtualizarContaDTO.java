package br.edu.fateczl.sistema_bancario.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AtualizarContaDTO {
    private String codigo;
    private BigDecimal saldo;
    private BigDecimal limite;
    private BigDecimal rendimento;
}