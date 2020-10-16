package io.github.leobugoni.pessoaFisica.controller;

import io.github.leobugoni.pessoaFisica.model.PessoaFisica;
import io.github.leobugoni.pessoaFisica.repository.RepositoryPessoaFisica;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/pessoa-fisica")
public class PessoaFisicaController {

    private RepositoryPessoaFisica repositoryPessoaFisica;

    public PessoaFisicaController(RepositoryPessoaFisica repositoryPessoaFisica) {
        this.repositoryPessoaFisica = repositoryPessoaFisica;
    }

    @PostMapping
    public PessoaFisica saveProduct(@RequestBody PessoaFisica pessoaFisica){
        return repositoryPessoaFisica.save(pessoaFisica);
    }

    @PutMapping
    public PessoaFisica updateProduct(@RequestBody PessoaFisica pessoaFisica){
        return repositoryPessoaFisica.save(pessoaFisica);
    }

    @GetMapping(path = "/{id}")
    public PessoaFisica findProduct(@PathVariable Long id){
        final Optional<PessoaFisica> pessoaFisicaOptional = repositoryPessoaFisica.findById(id);
        if(pessoaFisicaOptional.isPresent()){
            return pessoaFisicaOptional.get();
        } else {
            throw new RuntimeException(String.format("Pessoa fisica id {%s} não encontrada no banco de dados", id));
        }
    }

    @DeleteMapping
    public void deleteProduct(@RequestBody PessoaFisica pessoaFisica){
        repositoryPessoaFisica.delete(pessoaFisica);
    }
}
