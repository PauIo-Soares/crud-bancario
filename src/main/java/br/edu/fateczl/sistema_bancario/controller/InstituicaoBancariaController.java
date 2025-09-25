package br.edu.fateczl.sistema_bancario.controller;


import br.edu.fateczl.sistema_bancario.dto.InstituicaoBancariaDTO;
import br.edu.fateczl.sistema_bancario.model.InstituicaoBancaria;
import br.edu.fateczl.sistema_bancario.service.InstituicaoBancariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instituicao-bancaria")
public class InstituicaoBancariaController {

    @Autowired
    private InstituicaoBancariaService instituicaoBancariaService;

    @PostMapping
    public String inserirInstituicaoBancaria(@RequestBody InstituicaoBancariaDTO instituicaoBancaria) {
        instituicaoBancariaService.inserirInstituicaoBancaria(instituicaoBancaria);
        return "Instituição Bancária inserida com sucesso";
    }

    @GetMapping("/{codigo}")
    public InstituicaoBancaria buscarInstituicaoBancaria(@PathVariable Long codigo) {
        return instituicaoBancariaService.buscarInstituicaoBancaria(codigo);
    }

    @GetMapping
    public List<InstituicaoBancaria> listarInstituicoesBancarias() {
        return instituicaoBancariaService.listarInstituicoesBancarias();
    }

    @PutMapping
    public String atualizarInstituicaoBancaria(@RequestBody InstituicaoBancaria instituicao) {
        instituicaoBancariaService.atualizarInstituicaoBancaria(instituicao);
        return "Instituicao Bancaria modificada";
    }

    @DeleteMapping("/{codigo}")
    public String excluirInstituicaoBancaria(@PathVariable Long codigo) {
        instituicaoBancariaService.excluirInstituicaoBancaria(codigo);
        return "Instituição Bancária excluida com sucesso";
    }
}