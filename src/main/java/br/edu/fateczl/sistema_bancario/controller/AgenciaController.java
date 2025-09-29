package br.edu.fateczl.sistema_bancario.controller;

import br.edu.fateczl.sistema_bancario.dto.AgenciaDTO;

import br.edu.fateczl.sistema_bancario.service.AgenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agencia")
public class AgenciaController {

    @Autowired
    private AgenciaService agenciaService;

    @PostMapping
    public String inserirAgencia(@RequestBody AgenciaDTO agencia) {
        return agenciaService.inserirAgencia(agencia);
    }

    @GetMapping("/{codigo}")
    public AgenciaDTO buscarAgencia(@PathVariable Long codigo) {
        return agenciaService.buscarAgencia(codigo);
    }

    @PutMapping
    public String modificarAgencia(@RequestBody AgenciaDTO agencia) {
        agenciaService.modificarAgencia(agencia);
        return "Agencia modificada com sucesso";
    }

    @DeleteMapping("/{codigo}")
    public String excluirAgencia(@PathVariable Long codigo) {
        agenciaService.excluirAgencia(codigo);
        return "Agencia excluida com sucesso";
    }

    @GetMapping
    public List<AgenciaDTO> listarAgencias() {
        return agenciaService.listarAgencias();
    }
}