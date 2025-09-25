package br.edu.fateczl.sistema_bancario.service;

import br.edu.fateczl.sistema_bancario.dto.AgenciaDTO;
import br.edu.fateczl.sistema_bancario.model.Agencia;
import br.edu.fateczl.sistema_bancario.model.InstituicaoBancaria;
import br.edu.fateczl.sistema_bancario.persistence.AgenciaRepository;
import br.edu.fateczl.sistema_bancario.persistence.InstituicaoBancariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AgenciaService {

    @Autowired
    private AgenciaRepository agenciaRepository;

    @Autowired
    private InstituicaoBancariaRepository instituicaoBancariaRepository;

    public void inserirAgencia(AgenciaDTO dto) {
        InstituicaoBancaria instituicao = instituicaoBancariaRepository.findById(dto.getCodigoInstituicao()).orElseThrow(() -> new RuntimeException("Instituição não encontrada"));

        Agencia entidade = new Agencia();
        entidade.setCodigo(dto.getCodigo());
        entidade.setNome(dto.getNome());
        entidade.setCep(dto.getCep());
        entidade.setCidade(dto.getCidade());
        entidade.setInstituicao(instituicao);
        agenciaRepository.save(entidade);
    }

    public AgenciaDTO buscarAgencia(Long codigo){
        Agencia agencia = agenciaRepository.findById(codigo).orElseThrow(() -> new RuntimeException("Agencia não encontrada"));
        return new AgenciaDTO(
                agencia.getCodigo(),
                agencia.getNome(),
                agencia.getCep(),
                agencia.getCidade(),
                codigo
        );
    }

    public void modificarAgencia(AgenciaDTO dto) {
        Agencia agencia = agenciaRepository.findById(dto.getCodigo()).orElseThrow(() -> new RuntimeException("Instituição Bancaria não encontrada"));
        if (dto.getNome() != null) agencia.setNome(dto.getNome());
        if (dto.getCep() != null) agencia.setCep(dto.getCep());
        if (dto.getCidade() != null) agencia.setCidade(dto.getCidade());
        agenciaRepository.save(agencia);
    }

    public void excluirAgencia(Long codigo) {
        agenciaRepository.deleteById(codigo);
    }

    public List<AgenciaDTO> listarAgencias() {
        List<Agencia> listaEntidades = agenciaRepository.findAll();
        List<AgenciaDTO> resposta = new ArrayList<>();

        for (Agencia i : listaEntidades) {
            resposta.add(new AgenciaDTO(
                    i.getCodigo(),
                    i.getNome(),
                    i.getCep(),
                    i.getCidade(),
                    i.getCodigo()
            ));
        }
        return resposta;
    }
}