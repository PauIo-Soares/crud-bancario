package br.edu.fateczl.sistema_bancario.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ClienteProcedureRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String inserirCliente(String cpf, String nome, String senha) {
        return jdbcTemplate.execute("{call sp_cria_cliente(?, ?, ?, ?)}", (CallableStatementCallback<String>) cs -> {
            cs.setString(1, cpf);
            cs.setString(2, nome);
            cs.setString(3, senha);
            cs.registerOutParameter(4, java.sql.Types.VARCHAR);
            cs.execute();
            return cs.getString(4);
        });
    }

    public String alterarSenha(String cpf, String senha) {
        return jdbcTemplate.execute("{call sp_atualiza_senha(?, ?, ?)}", (CallableStatementCallback<String>) cs -> {
            cs.setString(1, cpf);
            cs.setString(2, senha);
            cs.registerOutParameter(3, java.sql.Types.VARCHAR);
            cs.execute();
            return cs.getString(3);
        });
    }

    public String excluirCliente(String cpf) {
        return jdbcTemplate.execute("{call sp_deleta_cliente(?, ?)}", (CallableStatementCallback<String>) cs -> {
            cs.setString(1, cpf);
            cs.registerOutParameter(2, java.sql.Types.VARCHAR);
            cs.execute();
            return cs.getString(2);
        });
    }
}