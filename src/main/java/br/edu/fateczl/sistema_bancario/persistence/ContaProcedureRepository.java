package br.edu.fateczl.sistema_bancario.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;

@Repository
public class ContaProcedureRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String criarConta(LocalDate dataAbertura, BigDecimal saldo, Long agenciaId) {
        return jdbcTemplate.execute(
                "{call sp_criar_conta(?, ?, ?, ?)}",
                (CallableStatementCallback<String>) cs -> {
                    cs.setDate(1, java.sql.Date.valueOf(dataAbertura));
                    cs.setBigDecimal(2, saldo);
                    cs.setLong(3, agenciaId);
                    cs.registerOutParameter(4, java.sql.Types.VARCHAR);
                    cs.execute();
                    return cs.getString(4);
                }
        );
    }
}