package br.com.contato.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.contato.model.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long>{

}
