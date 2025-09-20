package br.edu.fateczl.sistema_bancario.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public abstract class Conta {
    private Long codigo;
    private LocalDate DataAbertura;
    private BigDecimal saldo;
}
