package io.github.leobugoni.pessoaFisica.repository;

import io.github.leobugoni.pessoaFisica.model.PessoaFisica;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Configuration
public interface RepositoryPessoaFisica extends CrudRepository<PessoaFisica, Long> {

}
