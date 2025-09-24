package br.edu.fateczl.sistema_bancario.controller;


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
    public String inserirCliente(@RequestBody Cliente cliente) {
        clienteService.inserirCliente(cliente);
        return "Cliente inserido com sucesso";
    }

    @GetMapping("/{cpf}")
    public Cliente buscarCliente(@PathVariable String cpf) {
        return clienteService.buscarCliente(cpf);
    }

    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteService.listarClientes();
    }

    @PutMapping("/{cpf}")
    public String alterarSenha(@PathVariable String cpf, @RequestBody Cliente cliente) {
        clienteService.alterarSenha(cliente);
        return "Senha alterada com sucesso";
    }

    @DeleteMapping("/{cpf}")
    public String excluirCliente(@PathVariable String cpf) {
        clienteService.excluirCliente(cpf);
        return "Cliente excluida com sucesso";
    }

    //TODO
//    @PostMapping("/login")
//    public String login(@RequestBody LoginDTO login) {
//        boolean autenticado = clienteService.autenticar(login.getCpf(), login.getSenha());
//        if (autenticado) {
//            return "Login realizado com sucesso!";
//        } else {
//            return "CPF ou senha incorretos!";
//        }
//    }
}