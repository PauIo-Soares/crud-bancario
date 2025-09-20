package br.edu.fateczl.sistema_bancario.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ContaCorrente extends Conta{
    private BigDecimal limiteCredito;
}
