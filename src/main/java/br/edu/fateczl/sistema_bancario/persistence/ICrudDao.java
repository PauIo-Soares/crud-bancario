package br.edu.fateczl.sistema_bancario.persistence;

import java.sql.SQLException;
import java.util.List;

public interface ICrudDao<T> { //Todas as classes DAO farão as mesmas operações - Generics
    public void inserir (T t) throws SQLException, ClassNotFoundException;
    public void atualizar (T t) throws SQLException, ClassNotFoundException;
    public void excluir (T t) throws SQLException, ClassNotFoundException;
    public T buscar (T t) throws SQLException, ClassNotFoundException;
    public List<T> listar ()  throws SQLException, ClassNotFoundException;
}
