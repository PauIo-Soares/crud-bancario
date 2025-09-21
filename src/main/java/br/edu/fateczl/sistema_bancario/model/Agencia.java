package br.edu.fateczl.sistema_bancario.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "tb_agencias")
@Data
public class Agencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String cep;
    @Column(nullable = false)
    private String cidade;
    @ManyToOne
    @JoinColumn(name = "instituicao_id", nullable = false)
    private InstituicaoBancaria instituicao;
    @OneToMany(mappedBy = "agencia")
    private List<Conta> contas;
}