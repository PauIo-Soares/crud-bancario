package br.edu.fateczl.sistema_bancario.persistence;

import br.edu.fateczl.sistema_bancario.model.InstituicaoBancaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstituicaoBancariaRepository extends JpaRepository<InstituicaoBancaria, Long> {
}