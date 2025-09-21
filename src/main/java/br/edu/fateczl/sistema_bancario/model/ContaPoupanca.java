package br.edu.fateczl.sistema_bancario.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "tb_contas_poupancas")
@Data
public class ContaPoupanca extends Conta {
    @Column(nullable = false)
    private LocalDate dataAniversario;
    @Column(nullable = false)
    private BigDecimal rendimento;
}