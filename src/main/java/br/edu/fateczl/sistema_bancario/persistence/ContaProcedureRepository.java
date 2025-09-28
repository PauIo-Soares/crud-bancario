package br.edu.fateczl.sistema_bancario.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

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

    public void excluirConta() {
        //{call sp_excluir_conta}
    }

    public void adicionarSegundoTitular() {
        //{call sp_adicionar_titular_conta}
    }
}