package br.edu.fateczl.sistema_bancario.service;

import br.edu.fateczl.sistema_bancario.model.Conta;
import br.edu.fateczl.sistema_bancario.persistence.ContaProcedureRepository;
import br.edu.fateczl.sistema_bancario.persistence.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContaService {
    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private ContaProcedureRepository contaProcedureRepository;

    public String criarConta(Conta conta) {
        return contaProcedureRepository.criarConta(
                conta.getDataAbertura(),
                conta.getSaldo(),
                conta.getAgencia().getCodigo()
        );
    }

    public String atualizarSaldo(Conta conta) {
        // procedure
        contaProcedureRepository.atualizarSaldo();
        return "Dados da conta alterados com sucesso";
    }

    public String atualizarLimite(Conta conta) {
        // procedure
        contaProcedureRepository.atualizarLimite();
        return "Dados da conta alterados com sucesso";
    }

    public String atualizarRendimento(Conta conta) {
        // procedure
        contaProcedureRepository.atualizarRendimento();
        return "Dados da conta alterados com sucesso";
    }

    public String excluirConta(Long id) {
        // procedure
        contaProcedureRepository.excluirConta();
        return "Conta excluída com sucesso";
    }

    public Conta buscarConta(Long id){
        return contaRepository.findById(id).orElse(null);
    }

    public List<Conta> listarContas() {
        return contaRepository.findAll();
    }

//    public void adicionarSegundoTitular(Long id, String codigoCliente) {
//        // TODO
//        contaProcedureRepository.adicionarSegundoTitular();
//    }
//
//    public Conta buscarDadosContas(String cpf) {
//        //Da pra fazer sem procedure eu acho
//        //TODO basicamente pegar do banco e montar um DTO pra jogar, seria tipo um find all by id
//        return null;
//    }
}