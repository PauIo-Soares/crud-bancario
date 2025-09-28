package br.edu.fateczl.sistema_bancario.persistence;

import br.edu.fateczl.sistema_bancario.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {
    Optional<Conta> findByCodigo(String codigo);
}