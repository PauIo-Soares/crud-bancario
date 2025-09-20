package br.edu.fateczl.sistema_bancario.persistence;

import br.edu.fateczl.sistema_bancario.model.Cliente;

import java.sql.SQLException;
import java.util.List;

public class ClienteDao implements ICrudDao<Cliente> {
    @Override
    public void inserir(Cliente cliente) throws SQLException, ClassNotFoundException {

    }

    @Override
    public void atualizar(Cliente cliente) throws SQLException, ClassNotFoundException {

    }

    @Override
    public void excluir(Cliente cliente) throws SQLException, ClassNotFoundException {

    }

    @Override
    public Cliente buscar(Cliente cliente) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<Cliente> listar() throws SQLException, ClassNotFoundException {
        return List.of();
    }
}
