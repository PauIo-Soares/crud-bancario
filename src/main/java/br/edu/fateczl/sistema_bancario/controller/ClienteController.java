package br.edu.fateczl.sistema_bancario.controller;

import br.edu.fateczl.sistema_bancario.dto.AlterarSenhaDTO;
import br.edu.fateczl.sistema_bancario.dto.ClienteDTO;
import br.edu.fateczl.sistema_bancario.dto.ClienteNovoDTO;
import br.edu.fateczl.sistema_bancario.model.Cliente;
import br.edu.fateczl.sistema_bancario.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public String inserirCliente(@RequestBody ClienteNovoDTO cliente) {
        return clienteService.inserirCliente(cliente);
    }

    @GetMapping("/{cpf}")
    public ClienteDTO buscarCliente(@PathVariable String cpf) {
        return clienteService.buscarCliente(cpf);
    }

    @PutMapping("/alterar-senha")
    public String alterarSenha(@RequestBody AlterarSenhaDTO dto) {
        return clienteService.alterarSenha(dto);
    }

    @DeleteMapping("/{cpf}")
    public String excluirCliente(@PathVariable String cpf) {
        return clienteService.excluirCliente(cpf);
    }

    @GetMapping
    public List<ClienteDTO> listarClientes() {
        return clienteService.listarClientes();
    }
}