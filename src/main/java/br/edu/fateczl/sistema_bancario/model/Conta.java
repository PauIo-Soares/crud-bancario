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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "instit_seq")
    @SequenceGenerator(name = "instit_seq", sequenceName = "instit_seq", initialValue = 1000, allocationSize = 10)
    private Long id;
    @Column(nullable = false)
    private String codigo;
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