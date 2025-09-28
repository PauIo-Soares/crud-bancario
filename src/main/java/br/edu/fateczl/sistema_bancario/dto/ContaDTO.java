package br.edu.fateczl.sistema_bancario.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ContaDTO {
    private Long id;
    private String codigo;
    private LocalDate dataAbertura;
    private BigDecimal saldo;
}