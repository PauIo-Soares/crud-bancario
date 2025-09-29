package br.edu.fateczl.sistema_bancario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import br.edu.fateczl.sistema_bancario.dto.InstituicaoBancariaDTO;
import br.edu.fateczl.sistema_bancario.service.InstituicaoBancariaService;

import java.util.List;

@Controller
@RequestMapping("/instituicao")
public class InstituicaoBancariaControllerMvc {

    @Autowired
    private InstituicaoBancariaService instituicaoService;

    @GetMapping
    public String form(Model model) {
        model.addAttribute("instituicao", new InstituicaoBancariaDTO());
        model.addAttribute("instituicoes", instituicaoService.listarInstituicoesBancarias());
        return "instituicao";
    }

    @PostMapping
    public String handleAction(@ModelAttribute("instituicao") InstituicaoBancariaDTO instituicao,
                               @RequestParam("action") String action,
                               Model model) {
        String saida = "";
        List<InstituicaoBancariaDTO> lista = null;
        try {
            if ("Inserir".equalsIgnoreCase(action)) {
                instituicaoService.inserirInstituicaoBancaria(instituicao);
                saida = "Instituição inserida com sucesso";
            } else if ("Buscar".equalsIgnoreCase(action)) {
                InstituicaoBancariaDTO found = instituicaoService.buscarInstituicaoBancaria(instituicao.getCodigo());
                model.addAttribute("instituicao", found);
            } else if ("Atualizar".equalsIgnoreCase(action)) {
                instituicaoService.atualizarInstituicaoBancaria(instituicao);
                saida = "Instituição atualizada";
            } else if ("Excluir".equalsIgnoreCase(action)) {
                instituicaoService.excluirInstituicaoBancaria(instituicao.getCodigo());
                saida = "Instituição excluída";
            } else if ("Listar".equalsIgnoreCase(action)) {
                lista = instituicaoService.listarInstituicoesBancarias();
            }
        } catch (Exception ex) {
            model.addAttribute("erro", ex.getMessage());
        }
        model.addAttribute("saida", saida);
        model.addAttribute("instituicoes", lista == null ? instituicaoService.listarInstituicoesBancarias() : lista);

        if (!"Buscar".equalsIgnoreCase(action)) {
            model.addAttribute("instituicao", new InstituicaoBancariaDTO());
        }
        return "instituicao";
    }
}