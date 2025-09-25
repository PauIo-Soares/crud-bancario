package br.edu.fateczl.sistema_bancario.controller;


import br.edu.fateczl.sistema_bancario.dto.InstituicaoBancariaDTO;
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
    public InstituicaoBancariaDTO buscarInstituicaoBancaria(@PathVariable Long codigo) {
        return instituicaoBancariaService.buscarInstituicaoBancaria(codigo);
    }

    @PutMapping
    public String atualizarInstituicaoBancaria(@RequestBody InstituicaoBancariaDTO instituicao) {
        instituicaoBancariaService.atualizarInstituicaoBancaria(instituicao);
        return "Instituicao Bancaria modificada";
    }

    @DeleteMapping("/{codigo}")
    public String excluirInstituicaoBancaria(@PathVariable Long codigo) {
        instituicaoBancariaService.excluirInstituicaoBancaria(codigo);
        return "Instituição Bancária excluida com sucesso";
    }

    @GetMapping
    public List<InstituicaoBancariaDTO> listarInstituicoesBancarias() {
        return instituicaoBancariaService.listarInstituicoesBancarias();
    }

}