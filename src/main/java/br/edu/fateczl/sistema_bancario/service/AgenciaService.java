package br.edu.fateczl.sistema_bancario.service;

import br.edu.fateczl.sistema_bancario.dto.AgenciaDTO;
import br.edu.fateczl.sistema_bancario.model.Agencia;
import br.edu.fateczl.sistema_bancario.model.InstituicaoBancaria;
import br.edu.fateczl.sistema_bancario.persistence.AgenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgenciaService {
    @Autowired
    private AgenciaRepository agenciaRepository;

    public String inserirAgencia(AgenciaDTO dto) {
        InstituicaoBancaria instituicaoBancaria = new InstituicaoBancaria();
        instituicaoBancaria.setCodigo(dto.getInstituicaoCodigo());

        Agencia agencia = new Agencia();
        agencia.setNome(dto.getNome());
        agencia.setCep(dto.getCep());
        agencia.setCidade(dto.getCidade());
        agencia.setInstituicao(instituicaoBancaria);

        agenciaRepository.save(agencia);
        return "Agencia criada com sucesso";
    }

    public String modificarAgencia(Agencia agencia) {
        agenciaRepository.save(agencia);
        return "Agencia atualizada com sucesso";
    }

    public String excluirAgencia(Long codigo) {
        agenciaRepository.deleteById(codigo);
        return "Agencia excluída com sucesso";
    }

    public Agencia buscarAgencia(Long codigo){
        return agenciaRepository.findById(codigo).orElse(null);
    }

    public List<Agencia> listarAgencias() {
        return agenciaRepository.findAll();
    }
}