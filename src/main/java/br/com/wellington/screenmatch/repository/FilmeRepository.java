package br.com.wellington.screenmatch.repository;

import br.com.wellington.screenmatch.domain.filme.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface FilmeRepository extends JpaRepository<Filme, Long> {


}
