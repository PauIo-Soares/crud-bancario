package br.edu.fateczl.sistema_bancario.persistence;

import br.edu.fateczl.sistema_bancario.dto.AdicionarSegundoTitularDTO;
import br.edu.fateczl.sistema_bancario.dto.ContaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class ContaProcedureRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String criarConta(String cpf, String cpfConjunto, String nomeConjunto, String senhaConjunta, String idAgencia, String tipoConta) {
        return jdbcTemplate.execute("{call sp_cria_conta(?, ?, ?, ?, ?, ?, ?)}", (CallableStatementCallback<String>) cs -> {
            cs.setString(1, cpf);

            if (cpfConjunto == null) {
                cs.setNull(2, java.sql.Types.VARCHAR);
            } else {
                cs.setString(2, cpfConjunto);
            }

            if (nomeConjunto == null) {
                cs.setNull(3, java.sql.Types.VARCHAR);
            } else {
                cs.setString(3, nomeConjunto);
            }

            if (senhaConjunta == null) {
                cs.setNull(4, java.sql.Types.VARCHAR);
            } else {
                cs.setString(4, senhaConjunta);
            }

            cs.setString(5, idAgencia);
            cs.setString(6, tipoConta);
            cs.registerOutParameter(7, java.sql.Types.VARCHAR);
            cs.execute();
            return cs.getString(7);
        });
    }

    public String atualizarSaldo(String codigo, BigDecimal novoSaldo) {
        return jdbcTemplate.execute("{call sp_atualiza_saldo(?, ?, ?)}", (CallableStatementCallback<String>) cs -> {
            cs.setString(1, codigo);
            cs.setBigDecimal(2, novoSaldo);
            cs.registerOutParameter(3, java.sql.Types.VARCHAR);
            cs.execute();
            return cs.getString(3);
        });
    }

    public String atualizarLimite(String codigo, BigDecimal novoLimite) {
        return jdbcTemplate.execute("{call sp_atualiza_limite(?, ?, ?)}", (CallableStatementCallback<String>) cs -> {
            cs.setString(1, codigo);
            cs.setBigDecimal(2, novoLimite);
            cs.registerOutParameter(3, java.sql.Types.VARCHAR);
            cs.execute();
            return cs.getString(3);
        });
    }

    public String atualizarRendimento(String codigo, BigDecimal novoRendimento) {
        return jdbcTemplate.execute("{call sp_atualiza_rendimento(?, ?, ?)}", (CallableStatementCallback<String>) cs -> {
            cs.setString(1, codigo);
            cs.setBigDecimal(2, novoRendimento);
            cs.registerOutParameter(3, java.sql.Types.VARCHAR);
            cs.execute();
            return cs.getString(3);
        });
    }

    public String excluirConta(String codigo) {
        return jdbcTemplate.execute("{call sp_deleta_conta(?, ?)}", (CallableStatementCallback<String>) cs -> {
            cs.setString(1, codigo);
            cs.registerOutParameter(2, java.sql.Types.VARCHAR);
            cs.execute();
            return cs.getString(2);
        });
    }

    public String adicionarSegundoTitular(AdicionarSegundoTitularDTO segundoTitularDTO) {
        return jdbcTemplate.execute("{call sp_insere_segundo_titular(?, ?, ?, ?, ?, ?, ?)}", (CallableStatementCallback<String>) cs -> {
            cs.setString(1, segundoTitularDTO.getCpfCliente());
            cs.setString(2, segundoTitularDTO.getCpfConjunto());
            cs.setString(3, segundoTitularDTO.getNome());
            cs.setString(4, segundoTitularDTO.getSenha());
            cs.setString(5, segundoTitularDTO.getCodigoConta());
            cs.setDate(6, java.sql.Date.valueOf(segundoTitularDTO.getDataAbertura()));
            cs.registerOutParameter(7, java.sql.Types.VARCHAR);
            cs.execute();
            return cs.getString(7);
        });
    }

    public List<ContaDTO> findByClienteCpf(String cpf) {
        String sql = """
                    SELECT c.id, c.codigo, c.saldo, c.data_abertura, c.agencia_id
                    FROM tb_contas c
                    INNER JOIN tb_titulares_conta tc ON c.id = tc.conta_id
                    WHERE tc.cliente_id = ?
                """;
        return jdbcTemplate.query(sql, ps -> ps.setString(1, cpf), (rs, rowNum) -> {
            ContaDTO conta = new ContaDTO();
            conta.setId(rs.getLong("id"));
            conta.setCodigo(rs.getString("codigo"));
            conta.setSaldo(rs.getBigDecimal("saldo"));
            conta.setDataAbertura(rs.getDate("data_abertura").toLocalDate());
            return conta;
        });
    }
}