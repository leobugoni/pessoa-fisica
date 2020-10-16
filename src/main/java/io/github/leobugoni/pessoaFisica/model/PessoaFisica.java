package io.github.leobugoni.pessoaFisica.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.AUTO;

@Data
@Entity
@ToString
@NoArgsConstructor
public class PessoaFisica {
    private @Id @GeneratedValue(strategy= AUTO) Long id;
    private String nome;
    private String idade;
}
