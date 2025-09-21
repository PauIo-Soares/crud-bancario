package br.edu.fateczl.sistema_bancario.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tb_clientes")
@Data
public class Cliente {
    @Id
    private String cpf;
    private String nome;
    private LocalDate dataPrimeiraConta;
    private String senha;
    @ManyToMany(mappedBy = "titulares")
    private List<Conta> contas;
}