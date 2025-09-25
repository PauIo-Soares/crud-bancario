package br.edu.fateczl.sistema_bancario.controller;

import br.edu.fateczl.sistema_bancario.model.Conta;
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
    public String criarConta(@RequestBody Conta conta) {
        contaService.criarConta(conta);
        return "Conta inserido com sucesso";
    }

    @GetMapping("/{id}")
    public Conta buscarConta(@PathVariable Long id) {
        return contaService.buscarConta(id);
    }

    @PutMapping("/saldo")
    public String atualizarSaldo(@RequestBody Conta conta) {
        contaService.atualizarSaldo(conta);
        return "Conta alterada com sucesso";
    }

    @PutMapping("/limite")
    public String atualizarLimite(@RequestBody Conta conta) {
        contaService.atualizarLimite(conta);
        return "Conta alterada com sucesso";
    }

    @PutMapping("/rendimento")
    public String atualizarRendimento(@RequestBody Conta conta) {
        contaService.atualizarRendimento(conta);
        return "Conta alterada com sucesso";
    }

    @DeleteMapping("/{id}")
    public String excluirConta(@PathVariable Long id) {
        contaService.excluirConta(id);
        return "Conta excluida com sucesso";
    }

    @GetMapping
    public List<Conta> listarContas() {
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
