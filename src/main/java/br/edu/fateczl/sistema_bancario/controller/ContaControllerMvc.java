package br.edu.fateczl.sistema_bancario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import br.edu.fateczl.sistema_bancario.dto.CriarContaDTO;
import br.edu.fateczl.sistema_bancario.dto.AtualizarContaDTO;
import br.edu.fateczl.sistema_bancario.dto.ContaDTO;
import br.edu.fateczl.sistema_bancario.dto.AdicionarSegundoTitularDTO;
import br.edu.fateczl.sistema_bancario.service.ContaService;
import br.edu.fateczl.sistema_bancario.service.AgenciaService;

@Controller
@RequestMapping("/conta")
public class ContaControllerMvc {

    @Autowired
    private ContaService contaService;

    @Autowired
    private AgenciaService agenciaService;

    @GetMapping
    public String form(Model model) {
        model.addAttribute("criarConta", new CriarContaDTO());
        model.addAttribute("atualizar", new AtualizarContaDTO());
        model.addAttribute("conta", new ContaDTO());
        model.addAttribute("contas", contaService.listarContas());
        model.addAttribute("agencias", agenciaService.listarAgencias());
        return "conta";
    }

    @PostMapping
    public String handleAction(@ModelAttribute("criarConta") CriarContaDTO criarConta, @ModelAttribute("conta") ContaDTO contaDto, @RequestParam("action") String action, @ModelAttribute("atualizar") AtualizarContaDTO atualizarDto, Model model) {
        String saida = "";
        try {
            if ("Criar".equalsIgnoreCase(action)) {
                saida = contaService.criarConta(criarConta);
                model.addAttribute("criarConta", new CriarContaDTO());
            } else if ("Buscar".equalsIgnoreCase(action)) {
                ContaDTO c = contaService.buscarConta(contaDto.getCodigo());
                model.addAttribute("contaResult", c);
            } else if ("Atualizar-Saldo".equalsIgnoreCase(action)) {
                saida = contaService.atualizarSaldo(atualizarDto);
            } else if ("Atualizar-Limite".equalsIgnoreCase(action)) {
                saida = contaService.atualizarLimite(atualizarDto);
            } else if ("Atualizar-Rendimento".equalsIgnoreCase(action)) {
                saida = contaService.atualizarRendimento(atualizarDto);
            } else if ("Excluir".equalsIgnoreCase(action)) {
                saida = contaService.excluirConta(contaDto.getCodigo());
            }
        } catch (Exception ex) {
            model.addAttribute("erro", ex.getMessage());
        }
        model.addAttribute("saida", saida);
        model.addAttribute("contas", contaService.listarContas());
        model.addAttribute("agencias", agenciaService.listarAgencias());
        return "conta";
    }

    @GetMapping("/segundo-titular")
    public String showAddSecondTitularForm(@RequestParam("contaCodigo") String contaCodigo, Model model) {
        AdicionarSegundoTitularDTO dto = new AdicionarSegundoTitularDTO();
        dto.setCodigoConta(contaCodigo);
        model.addAttribute("secondTitular", dto);
        model.addAttribute("contaCodigo", contaCodigo);
        return "conta-segundo-titular";
    }

    @PostMapping("/segundo-titular/preconfirm")
    public String preconfirmSecondTitular(@ModelAttribute("secondTitular") AdicionarSegundoTitularDTO segundo, @RequestParam("contaCodigo") String contaCodigo, Model model) {
        model.addAttribute("next", "confirm-add-second-titular");
        model.addAttribute("login", new br.edu.fateczl.sistema_bancario.dto.LoginDTO());
        model.addAttribute("secondTitular", segundo);
        model.addAttribute("contaCodigo", contaCodigo);
        return "login";
    }
}
