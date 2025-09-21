package br.edu.fateczl.sistema_bancario.persistence;

import br.edu.fateczl.sistema_bancario.model.Agencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgenciaRepository extends JpaRepository<Agencia, Long> {
}
