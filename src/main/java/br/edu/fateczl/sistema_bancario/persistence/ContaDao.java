package br.edu.fateczl.sistema_bancario.persistence;

import br.edu.fateczl.sistema_bancario.model.Conta;

import java.sql.SQLException;
import java.util.List;

public class ContaDao implements ICrudDao<Conta> {
    @Override
    public void inserir(Conta conta) throws SQLException, ClassNotFoundException {

    }

    @Override
    public void atualizar(Conta conta) throws SQLException, ClassNotFoundException {

    }

    @Override
    public void excluir(Conta conta) throws SQLException, ClassNotFoundException {

    }

    @Override
    public Conta buscar(Conta conta) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<Conta> listar() throws SQLException, ClassNotFoundException {
        return List.of();
    }
}
