package br.edu.fateczl.sistema_bancario.service;

import br.edu.fateczl.sistema_bancario.model.InstituicaoBancaria;
import br.edu.fateczl.sistema_bancario.persistence.InstituicaoBancariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstituicaoBancariaService {
    @Autowired
    private InstituicaoBancariaRepository instituicaoBancariaRepository;

    public String inserirInstituicaoBancaria(InstituicaoBancaria instituicaoBancaria) {
        instituicaoBancariaRepository.save(instituicaoBancaria);
        return "Instituicao Bancaria criada com sucesso";
    }

    public String atualizarInstituicaoBancaria(InstituicaoBancaria instituicaoBancaria) {
        instituicaoBancariaRepository.save(instituicaoBancaria);
        return "Instituicao Bancaria atualizada com sucesso";
    }

    public String excluirInstituicaoBancaria(Long codigo) {
        instituicaoBancariaRepository.deleteById(codigo);
        return "Instituicao Bancaria excluída com sucesso";
    }

    public InstituicaoBancaria buscarInstituicaoBancaria(Long codigo){
        return instituicaoBancariaRepository.findById(codigo).orElse(null);
    }

    public List<InstituicaoBancaria> listarInstituicoesBancarias() {
        return instituicaoBancariaRepository.findAll();
    }
}