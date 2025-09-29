package br.edu.fateczl.sistema_bancario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import br.edu.fateczl.sistema_bancario.dto.ClienteNovoDTO;
import br.edu.fateczl.sistema_bancario.dto.ClienteDTO;
import br.edu.fateczl.sistema_bancario.dto.AlterarSenhaDTO;
import br.edu.fateczl.sistema_bancario.service.ClienteService;
import br.edu.fateczl.sistema_bancario.service.AgenciaService;

@Controller
@RequestMapping("/cliente")
public class ClienteControllerMvc {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private AgenciaService agenciaService;

    @GetMapping
    public String form(Model model) {
        model.addAttribute("cliente", new ClienteNovoDTO());
        model.addAttribute("agencias", agenciaService.listarAgencias());
        model.addAttribute("clientes", clienteService.listarClientes());
        return "cliente";
    }

    @PostMapping
    public String handleAction(@ModelAttribute("cliente") ClienteNovoDTO cliente,
                               @RequestParam("action") String action,
                               Model model) {
        String saida = "";
        try {
            if ("Inserir".equalsIgnoreCase(action)) {
                saida = clienteService.inserirCliente(cliente);
                model.addAttribute("cliente", new ClienteNovoDTO());
            } else if ("Buscar".equalsIgnoreCase(action)) {
                ClienteDTO c = clienteService.buscarCliente(cliente.getCpf());
                model.addAttribute("cliente", c);
            } else if ("Excluir".equalsIgnoreCase(action)) {
                saida = clienteService.excluirCliente(cliente.getCpf());
                model.addAttribute("cliente", new ClienteNovoDTO());
            } else if ("Listar".equalsIgnoreCase(action)) {
                model.addAttribute("clientes", clienteService.listarClientes());
            }
        } catch (Exception ex) {
            model.addAttribute("erro", ex.getMessage());
        }
        model.addAttribute("saida", saida);
        model.addAttribute("agencias", agenciaService.listarAgencias());
        return "cliente";
    }

    @PostMapping("/alterar-senha")
    public String alterarSenha(@ModelAttribute AlterarSenhaDTO dto, Model model) {
        String saida = clienteService.alterarSenha(dto);
        model.addAttribute("saida", saida);
        model.addAttribute("cliente", new ClienteNovoDTO());
        model.addAttribute("agencias", agenciaService.listarAgencias());
        return "cliente";
    }
}
