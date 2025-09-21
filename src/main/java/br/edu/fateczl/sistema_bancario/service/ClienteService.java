package br.edu.fateczl.sistema_bancario.service;

import br.edu.fateczl.sistema_bancario.model.Cliente;
import br.edu.fateczl.sistema_bancario.persistence.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente buscarPorCpf(String cpf){
        return clienteRepository.findById(cpf).orElse(null);
    }

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }
}