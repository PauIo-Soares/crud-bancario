package br.edu.fateczl.sistema_bancario.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;

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

    public String alterarSenha(String cpf, String senhaAtual, String novaSenha) {
        return jdbcTemplate.execute("{call sp_atualiza_senha(?, ?, ?, ?)}", (CallableStatementCallback<String>) cs -> {
            cs.setString(1, cpf);
            cs.setString(2, senhaAtual);
            cs.setString(3, novaSenha);
            cs.registerOutParameter(4, java.sql.Types.VARCHAR);
            cs.execute();
            return cs.getString(4);
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

    public boolean autenticarCliente(String cpf, String senha) {
        return jdbcTemplate.execute("{call sp_autentica_cliente(?, ?, ?)}", (CallableStatementCallback<Boolean>) cs -> {
            cs.setString(1, cpf);
            cs.setString(2, senha);
            cs.registerOutParameter(3, Types.BIT);
            cs.execute();
            return cs.getBoolean(3);
        });
    }
}