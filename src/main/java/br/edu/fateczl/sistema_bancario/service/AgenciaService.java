package br.edu.fateczl.sistema_bancario.service;

import br.edu.fateczl.sistema_bancario.model.Agencia;
import br.edu.fateczl.sistema_bancario.persistence.AgenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgenciaService {
    @Autowired
    private AgenciaRepository agenciaRepository;

    public String criarAgencia(Agencia agencia) {
        agenciaRepository.save(agencia);
        return "Agencia criada com sucesso";
    }

    public String modificarAgencia(Agencia agencia) {
        agenciaRepository.save(agencia);
        return "Agencia atualizada com sucesso";
    }

    public String excluirAgenciaPorCodigo(Long codigo) {
        agenciaRepository.deleteById(codigo);
        return "Agencia excluída com sucesso";
    }

    public Agencia buscarPorCodigo(Long codigo){
        return agenciaRepository.findById(codigo).orElse(null);
    }

    public List<Agencia> listarTodas() {
        return agenciaRepository.findAll();
    }
}