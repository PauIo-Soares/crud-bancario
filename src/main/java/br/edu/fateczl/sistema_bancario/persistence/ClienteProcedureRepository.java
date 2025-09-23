package br.edu.fateczl.sistema_bancario.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public class ClienteProcedureRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String criarCliente(String cpf, String nome, LocalDate dataPrimeiraConta, String senha) {
        return jdbcTemplate.execute(
                "{call sp_criar_cliente(?, ?, ?, ?, ?)}",
                (CallableStatementCallback<String>) cs -> {
                    cs.setString(1, cpf);
                    cs.setString(2, nome);
                    cs.setDate(3, java.sql.Date.valueOf(dataPrimeiraConta));
                    cs.setString(4, senha);
                    cs.registerOutParameter(5, java.sql.Types.VARCHAR);
                    cs.execute();
                    return cs.getString(5);
                }
        );
    }
}