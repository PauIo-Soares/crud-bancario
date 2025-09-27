package br.edu.fateczl.sistema_bancario.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_instituicoes_bancarias")
@Data
public class InstituicaoBancaria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String cep;
    @Column(nullable = false)
    private String cidade;
}