package br.edu.fateczl.sistema_bancario.controller;

import br.edu.fateczl.sistema_bancario.dto.*;
import br.edu.fateczl.sistema_bancario.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/conta")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @PostMapping
    public String criarConta(@RequestBody CriarContaDTO conta) {
        return contaService.criarConta(conta);
    }

    @GetMapping("/{codigo}")
    public ContaDTO buscarConta(@PathVariable String codigo) {
        return contaService.buscarConta(codigo);
    }

    @PutMapping("/saldo")
    public String atualizarSaldo(@RequestBody AtualizarContaDTO conta) {
        return contaService.atualizarSaldo(conta);
    }

    @PutMapping("/limite")
    public String atualizarLimite(@RequestBody AtualizarContaDTO conta) {
        return contaService.atualizarLimite(conta);
    }

    @PutMapping("/rendimento")
    public String atualizarRendimento(@RequestBody AtualizarContaDTO conta) {
        return contaService.atualizarRendimento(conta);
    }

    @DeleteMapping("/{codigo}")
    public String excluirConta(@PathVariable String codigo) {
        return contaService.excluirConta(codigo);
    }

    @GetMapping
    public List<ContaDTO> listarContas() {
        return contaService.listarContas();
    }

    @PostMapping("/segundo-titular")
    public String adicionarSegundoTitular(@RequestBody AdicionarTitularRequest request) {
        return contaService.adicionarSegundoTitular(request.getLoginTitular(), request.getSegundoTitularDTO());
    }

    @GetMapping("/dados")
    public List<ContaDTO> buscarDadosContas(@RequestBody LoginDTO login) {
        return contaService.buscarDadosContas(login);
    }
}