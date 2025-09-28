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
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public String inserirCliente(@RequestBody ClienteNovoDTO cliente) {
        return clienteService.inserirCliente(cliente);
    }

    @GetMapping("/{cpf}")
    public Cliente buscarCliente(@PathVariable String cpf) {
        return clienteService.buscarCliente(cpf);
    }

    @PutMapping("/{cpf}")
    public String alterarSenha(@PathVariable String cpf, @RequestBody AlterarSenhaDTO senha) {
        return clienteService.alterarSenha(cpf, senha.getNovaSenha());
    }

    @DeleteMapping("/{cpf}")
    public String excluirCliente(@PathVariable String cpf) {
        return clienteService.excluirCliente(cpf);
    }

    @GetMapping
    public List<ClienteDTO> listarClientes() {
        return clienteService.listarClientes();
    }

//TODO
//    @PostMapping("/login")
//    public String autenticar(@RequestBody LoginDTO login) {
//        boolean autenticado = clienteService.autenticar(login.getCpf(), login.getSenha());
//        if (autenticado) {
//            return "Login realizado com sucesso!";
//        } else {
//            return "CPF ou senha incorretos!";
//        }
//    }
}