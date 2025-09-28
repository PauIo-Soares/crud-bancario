package br.edu.fateczl.sistema_bancario.dto;

import lombok.Data;

@Data
public class AdicionarTitularRequest {
    private LoginDTO loginTitular;
    private AdicionarSegundoTitularDTO segundoTitularDTO;
}