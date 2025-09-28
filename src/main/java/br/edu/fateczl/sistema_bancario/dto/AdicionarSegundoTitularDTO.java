package br.edu.fateczl.sistema_bancario.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AdicionarSegundoTitularDTO {
    private String cpfCliente;
    private String cpfConjunto;
    private String nome;
    private String senha;
    private String codigoConta;
    private LocalDate dataAbertura;
}