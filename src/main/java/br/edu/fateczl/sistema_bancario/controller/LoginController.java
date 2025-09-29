package br.edu.fateczl.sistema_bancario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.fateczl.sistema_bancario.dto.LoginDTO;
import br.edu.fateczl.sistema_bancario.dto.ContaDTO;
import br.edu.fateczl.sistema_bancario.dto.AdicionarSegundoTitularDTO;
import br.edu.fateczl.sistema_bancario.service.ContaService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private ContaService contaService;

    @GetMapping("/login")
    public String loginForm(@RequestParam(value = "next", required = false) String next, Model model) {
        model.addAttribute("login", new LoginDTO());
        model.addAttribute("next", next);
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@ModelAttribute LoginDTO login,
                          @RequestParam(value = "next", required = false) String next,
                          @RequestParam Map<String,String> params,
                          Model model) {
        try {
            if ("dados".equals(next)) {
                // ver dados das contas do cliente
                List<ContaDTO> contas = contaService.buscarDadosContas(login);
                model.addAttribute("contas", contas);
                return "dados-contas";
            } else if ("confirm-add-second-titular".equals(next)) {
                // lê os campos hidden enviados junto com o login e reconstrói o DTO
                AdicionarSegundoTitularDTO segundo = new AdicionarSegundoTitularDTO();
                segundo.setCpfConjunto(params.get("cpfConjunto"));
                segundo.setNome(params.get("nome"));
                segundo.setSenha(params.get("senha")); // se enviado
                segundo.setCodigoConta(params.get("contaCodigo"));
                // data (opcional)
                String dataAberturaStr = params.get("dataAbertura");
                if (dataAberturaStr != null && !dataAberturaStr.isEmpty()) {
                    try {
                        segundo.setDataAbertura(LocalDate.parse(dataAberturaStr));
                    } catch (Exception ignored) { }
                }
                // chama service que espera (LoginDTO, AdicionarSegundoTitularDTO)
                String saida = contaService.adicionarSegundoTitular(login, segundo);
                model.addAttribute("saida", saida);
                model.addAttribute("contas", contaService.listarContas());
                return "conta";
            } else {
                model.addAttribute("erro", "Ação de login desconhecida.");
                return "login";
            }
        } catch (Exception ex) {
            model.addAttribute("erro", ex.getMessage());
            return "login";
        }
    }
}