package br.edu.fateczl.sistema_bancario.persistence;

import br.edu.fateczl.sistema_bancario.model.Agencia;

import java.sql.SQLException;
import java.util.List;

public class AgenciaDao implements ICrudDao<Agencia> {
    @Override
    public void inserir(Agencia agencia) throws SQLException, ClassNotFoundException {

    }

    @Override
    public void atualizar(Agencia agencia) throws SQLException, ClassNotFoundException {

    }

    @Override
    public void excluir(Agencia agencia) throws SQLException, ClassNotFoundException {

    }

    @Override
    public Agencia buscar(Agencia agencia) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<Agencia> listar() throws SQLException, ClassNotFoundException {
        return List.of();
    }
}
