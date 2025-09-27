package br.edu.fateczl.sistema_bancario.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "tb_clientes")
@Data
public class Cliente {
    @Id
    private String cpf;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private LocalDate dataPrimeiraConta;
    @Column(nullable = false)
    private String senha;
}