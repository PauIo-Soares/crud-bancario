package br.edu.fateczl.sistema_bancario.controller;

import br.edu.fateczl.sistema_bancario.dto.AtualizarContaDTO;
import br.edu.fateczl.sistema_bancario.dto.ContaDTO;
import br.edu.fateczl.sistema_bancario.dto.CriarContaDTO;
import br.edu.fateczl.sistema_bancario.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conta")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @PostMapping
    public String criarConta(@RequestBody CriarContaDTO conta) {
        return contaService.criarConta(conta);
    }

    @GetMapping("/{codigo}")
    public ContaDTO buscarConta(@PathVariable String codigo) {
        return contaService.buscarConta(codigo);
    }

    @PutMapping("/saldo")
    public String atualizarSaldo(@RequestBody AtualizarContaDTO conta) {
        return contaService.atualizarSaldo(conta);
    }

    @PutMapping("/limite")
    public String atualizarLimite(@RequestBody AtualizarContaDTO conta) {
        return contaService.atualizarLimite(conta);
    }

    @PutMapping("/rendimento")
    public String atualizarRendimento(@RequestBody AtualizarContaDTO conta) {
        return contaService.atualizarRendimento(conta);
    }

    @DeleteMapping("/{codigo}")
    public String excluirConta(@PathVariable String codigo) {
        return contaService.excluirConta(codigo);
    }

    @GetMapping
    public List<ContaDTO> listarContas() {
        return contaService.listarContas();
    }

//    TODO
//    @PostMapping("/{codigoConta}/titular/{codigoCliente}")
//    public String adicionarSegundoTitular(@PathVariable Long codigoConta, @PathVariable String codigoCliente) {
//        contaService.adicionarSegundoTitular(codigoConta, codigoCliente); //Revisar
//        return "Segundo titular adicionado com sucesso!";
//    }
//    TODO
//    @GetMapping("/dados/{cpf}")
//    public ContaDTO buscarDadosContas(@PathVariable String cpf) {
//        Conta conta = contaService.buscarDadosContas(cpf);
//        ContaDTO dto = new ContaDTO();
//        dto.setSaldo(conta.getSaldo());
//        dto.setLimite(conta.getLimite());
//        dto.setRendimento(conta.getRendimento());
//        return dto;
//    }
}