package br.edu.fateczl.sistema_bancario.service;

import br.edu.fateczl.sistema_bancario.dto.InstituicaoBancariaDTO;
import br.edu.fateczl.sistema_bancario.model.InstituicaoBancaria;
import br.edu.fateczl.sistema_bancario.persistence.InstituicaoBancariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InstituicaoBancariaService {

    @Autowired
    private InstituicaoBancariaRepository instituicaoBancariaRepository;

    public void inserirInstituicaoBancaria(InstituicaoBancariaDTO dto) {
        InstituicaoBancaria entidade = new InstituicaoBancaria();
        entidade.setCodigo(dto.getCodigo());
        entidade.setNome(dto.getNome());
        entidade.setCep(dto.getCep());
        entidade.setCidade(dto.getCidade());
        instituicaoBancariaRepository.save(entidade);
    }

    public InstituicaoBancariaDTO buscarInstituicaoBancaria(Long codigo) {
        InstituicaoBancaria instituicao = instituicaoBancariaRepository.findById(codigo).orElseThrow(() -> new RuntimeException("Instituição Bancaria não encontrada"));
        return new InstituicaoBancariaDTO(
                instituicao.getCodigo(),
                instituicao.getNome(),
                instituicao.getCep(),
                instituicao.getCidade()
        );
    }

    public void atualizarInstituicaoBancaria(InstituicaoBancariaDTO dto) {
        InstituicaoBancaria entidade = instituicaoBancariaRepository.findById(dto.getCodigo()).orElseThrow(() -> new RuntimeException("Instituição Bancaria não encontrada"));
        if (dto.getNome() != null) entidade.setNome(dto.getNome());
        if (dto.getCep() != null) entidade.setCep(dto.getCep());
        if (dto.getCidade() != null) entidade.setCidade(dto.getCidade());
        instituicaoBancariaRepository.save(entidade);
    }

    public void excluirInstituicaoBancaria(Long codigo) {
        instituicaoBancariaRepository.deleteById(codigo);
    }

    public List<InstituicaoBancariaDTO> listarInstituicoesBancarias() {
        List<InstituicaoBancaria> listaEntidades = instituicaoBancariaRepository.findAll();
        List<InstituicaoBancariaDTO> resposta = new ArrayList<>();

        for (InstituicaoBancaria i : listaEntidades) {
            resposta.add(new InstituicaoBancariaDTO(
                    i.getCodigo(),
                    i.getNome(),
                    i.getCep(),
                    i.getCidade()
            ));
        }
        return resposta;
    }
}