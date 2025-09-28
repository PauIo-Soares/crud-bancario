package br.edu.fateczl.sistema_bancario.service;

import br.edu.fateczl.sistema_bancario.dto.ClienteNovoDTO;
import br.edu.fateczl.sistema_bancario.enums.TipoConta;
import br.edu.fateczl.sistema_bancario.model.Cliente;
import br.edu.fateczl.sistema_bancario.persistence.ClienteProcedureRepository;
import br.edu.fateczl.sistema_bancario.persistence.ClienteRepository;
import br.edu.fateczl.sistema_bancario.persistence.ContaProcedureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteProcedureRepository clienteProcedureRepository;

    @Autowired
    private ContaProcedureRepository contaProcedureRepository;

    public String inserirCliente(ClienteNovoDTO cliente) {
        String tipoContaDb = cliente.getTipoConta() == TipoConta.CORRENTE ? "C" : "P";

        String retornoCliente = clienteProcedureRepository.inserirCliente(cliente.getCpf(), cliente.getNome(), cliente.getSenha());
        String retornoConta = contaProcedureRepository.criarConta(cliente.getCpf(), null, null, null, cliente.getCodigoAgencia(), tipoContaDb);

        return retornoCliente + " | " + retornoConta;
    }

    public Cliente buscarCliente(String cpf) {
        return clienteRepository.findById(cpf).orElse(null);
    }

    public String alterarSenha(String cpf, String novaSenha) {
        return clienteProcedureRepository.alterarSenha(cpf, novaSenha);
    }

    public String excluirCliente(String cpf) {
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