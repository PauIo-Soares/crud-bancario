package br.edu.fateczl.sistema_bancario.service;

import br.edu.fateczl.sistema_bancario.model.Cliente;
import br.edu.fateczl.sistema_bancario.persistence.ClienteProcedureRepository;
import br.edu.fateczl.sistema_bancario.persistence.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteProcedureRepository clienteProcedureRepository;

    public String inserirCliente(Cliente cliente) {
        return clienteProcedureRepository.criarCliente(
                cliente.getCpf(),
                cliente.getNome(),
                cliente.getDataPrimeiraConta(),
                cliente.getSenha()
        );
    }

    public String modificarCliente(Cliente cliente) {
         clienteRepository.save(cliente);
        return "Cliente modificado com sucesso";
    }

    public String excluirClientePorCodigo(String cpf) {
        // procedure
        return "Cliente excluído com sucesso";
    }

    public Cliente buscarPorCpf(String cpf){
        return clienteRepository.findById(cpf).orElse(null);
    }

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }
}