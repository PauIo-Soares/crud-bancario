package br.edu.fateczl.sistema_bancario.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tb_contas")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public abstract class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    @Column(nullable = false)
    private LocalDate dataAbertura;
    @Column(nullable = false)
    private BigDecimal saldo;
    @ManyToOne
    @JoinColumn(name = "agencia_id", nullable = false)
    private Agencia agencia;
    @ManyToMany
    @JoinTable(
            name = "tb_titulares_conta",
            joinColumns = @JoinColumn(name = "conta_id"),
            inverseJoinColumns = @JoinColumn(name = "cliente_id")
    )
    private List<Cliente> titulares;
}