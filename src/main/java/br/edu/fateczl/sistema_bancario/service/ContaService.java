package br.edu.fateczl.sistema_bancario.service;

import br.edu.fateczl.sistema_bancario.dto.*;
import br.edu.fateczl.sistema_bancario.enums.TipoConta;
import br.edu.fateczl.sistema_bancario.model.Conta;
import br.edu.fateczl.sistema_bancario.persistence.ContaProcedureRepository;
import br.edu.fateczl.sistema_bancario.persistence.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ContaProcedureRepository contaProcedureRepository;

    public String criarConta(CriarContaDTO conta) {
        String tipoContaDb = conta.getTipoConta() == TipoConta.CORRENTE ? "C" : "P";
        return contaProcedureRepository.criarConta(conta.getCpf(), conta.getCpfConjunto(), conta.getNomeConjunto(), conta.getSenhaConjunta(), conta.getCodigoAgencia(), tipoContaDb);
    }

    public ContaDTO buscarConta(String codigo) {
        Conta conta = contaRepository.findByCodigo(codigo).orElseThrow(() -> new RuntimeException("Conta não encontrada!"));
        return new ContaDTO(conta.getId(), conta.getCodigo(), conta.getDataAbertura(), conta.getSaldo());
    }

    public String atualizarSaldo(AtualizarContaDTO conta) {
        return contaProcedureRepository.atualizarSaldo(conta.getCodigo(), conta.getSaldo());
    }

    public String atualizarLimite(AtualizarContaDTO conta) {
        return contaProcedureRepository.atualizarLimite(conta.getCodigo(), conta.getLimite());
    }

    public String atualizarRendimento(AtualizarContaDTO conta) {
        return contaProcedureRepository.atualizarRendimento(conta.getCodigo(), conta.getRendimento());
    }

    public String excluirConta(String codigo) {
        return contaProcedureRepository.excluirConta(codigo);
    }

    public List<ContaDTO> listarContas() {
        List<Conta> contas = contaRepository.findAll();
        List<ContaDTO> contasDTO = new ArrayList<>();

        for (Conta conta : contas) {
            ContaDTO dto = new ContaDTO(conta.getId(), conta.getCodigo(), conta.getDataAbertura(), conta.getSaldo());
            contasDTO.add(dto);
        }
        return contasDTO;
    }

    public String adicionarSegundoTitular(LoginDTO login, AdicionarSegundoTitularDTO segundoTitularDTO) {
        boolean autenticado = clienteService.isAutenticado(login.getCpf(), login.getSenha());
        if (!autenticado) {
            throw new RuntimeException("Login do titular principal inválido.");
        }
        return contaProcedureRepository.adicionarSegundoTitular(segundoTitularDTO);
    }

    public List<ContaDTO> buscarDadosContas(LoginDTO login) {
        boolean autenticado = clienteService.isAutenticado(login.getCpf(), login.getSenha());
        if (!autenticado) {
            throw new RuntimeException("Login inválido.");
        }
        return contaProcedureRepository.findByClienteCpf(login.getCpf());
    }
}