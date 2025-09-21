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

    public Agencia buscarPorCodigo(Long codigo){
        return agenciaRepository.findById(codigo).orElse(null);
    }

    public List<Agencia> listarTodas() {
        return agenciaRepository.findAll();
    }
}