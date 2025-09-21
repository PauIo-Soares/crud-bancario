package br.edu.fateczl.sistema_bancario.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_contas_correntes")
@Data
public class ContaCorrente extends Conta{
    @Column(nullable = false)
    private BigDecimal limiteCredito;
}