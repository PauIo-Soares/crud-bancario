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
        return clienteProcedureRepository.inserirCliente(
                cliente.getCpf(),
                cliente.getNome(),
                cliente.getDataPrimeiraConta(),
                cliente.getSenha()
        );
    }

    public Cliente buscarCliente(String cpf){
        return clienteRepository.findById(cpf).orElse(null);
    }

    public void alterarSenha(Cliente cliente) {
        clienteProcedureRepository.alterarSenha(cliente);
        // TODO procedure
    }

    public String excluirCliente(String cpf) {
        //TODO procedure
        clienteProcedureRepository.excluirCliente(cpf);
        return "Cliente excluído com sucesso";
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    //TODO autenticar
//   public void autenticar(){
//
//   }
}