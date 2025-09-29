package br.edu.fateczl.sistema_bancario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import br.edu.fateczl.sistema_bancario.dto.AgenciaDTO;
import br.edu.fateczl.sistema_bancario.service.AgenciaService;
import br.edu.fateczl.sistema_bancario.service.InstituicaoBancariaService;

@Controller
@RequestMapping("/agencia")
public class AgenciaControllerMvc {

    @Autowired
    private AgenciaService agenciaService;

    @Autowired
    private InstituicaoBancariaService instituicaoService;

    @GetMapping
    public String form(Model model) {
        model.addAttribute("agencia", new AgenciaDTO());
        model.addAttribute("agencias", agenciaService.listarAgencias());
        model.addAttribute("instituicoes", instituicaoService.listarInstituicoesBancarias());
        return "agencia";
    }

    @PostMapping
    public String handleAction(@ModelAttribute("agencia") AgenciaDTO agencia,
                               @RequestParam("action") String action,
                               Model model) {
        String saida = "";
        List<AgenciaDTO> lista = null;
        try {
            if ("Inserir".equalsIgnoreCase(action)) {
                agenciaService.inserirAgencia(agencia);
                saida = "Agência inserida com sucesso";
            } else if ("Buscar".equalsIgnoreCase(action)) {
                AgenciaDTO found = agenciaService.buscarAgencia(agencia.getCodigo());
                model.addAttribute("agencia", found);
            } else if ("Atualizar".equalsIgnoreCase(action)) {
                agenciaService.modificarAgencia(agencia);
                saida = "Agência atualizada com sucesso";
            } else if ("Excluir".equalsIgnoreCase(action)) {
                agenciaService.excluirAgencia(agencia.getCodigo());
                saida = "Agência excluída com sucesso";
            } else if ("Listar".equalsIgnoreCase(action)) {
                lista = agenciaService.listarAgencias();
            }
        } catch (Exception ex) {
            model.addAttribute("erro", ex.getMessage());
        }

        model.addAttribute("saida", saida);
        model.addAttribute("agencias", lista == null ? agenciaService.listarAgencias() : lista);
        model.addAttribute("instituicoes", instituicaoService.listarInstituicoesBancarias());

        if (!"Buscar".equalsIgnoreCase(action)) {
            model.addAttribute("agencia", new AgenciaDTO());
        }
        return "agencia";
    }
}