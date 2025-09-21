package br.edu.fateczl.sistema_bancario.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "tb_instituicoes_bancarias")
@Data
public class InstituicaoBancaria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    private String nome;
    private String cep;
    private String cidade;
    @OneToMany(mappedBy = "instituicao")
    private List<Agencia> agencias;
}