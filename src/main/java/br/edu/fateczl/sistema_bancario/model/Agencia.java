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
    private String nome;
    private String cep;
    private String cidade;
    @ManyToOne
    @JoinColumn(name = "instituicao_id")
    private InstituicaoBancaria instituicao;
    @OneToMany(mappedBy = "agencia")
    private List<Conta> contas;
}