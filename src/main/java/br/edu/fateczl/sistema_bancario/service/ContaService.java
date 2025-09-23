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

    public String modificarConta(Conta conta) {
        // procedure
        return "Conta modificada com sucesso";
    }

    public String excluirContaPorCodigo(String codigo) {
        // procedure
        return "Conta excluída com sucesso";
    }

    public Conta buscarPorCodigo(Long codigo){
        return contaRepository.findById(codigo).orElse(null);
    }

    public List<Conta> listarTodas() {
        return contaRepository.findAll();
    }
}