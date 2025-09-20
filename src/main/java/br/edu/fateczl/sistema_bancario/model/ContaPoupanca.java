package br.edu.fateczl.sistema_bancario.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ContaPoupanca extends Conta {
    private LocalDate dataAniversario;
    private BigDecimal rendimento;
}
