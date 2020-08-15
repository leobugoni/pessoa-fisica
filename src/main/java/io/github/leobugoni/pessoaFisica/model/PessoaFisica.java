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
    private String cpf;
    private String rg;
    private String data_nasc;
    private String sexo;
    private String signo;
    private String mae;
    private String pai;
    private String email;
    private String senha;
    private String cep;
    private String endereco;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String telefone_fixo;
    private String celular;
    private String altura;
    private String peso;
    private String tipo_sanguineo;
    private String cor;

    public PessoaFisica(String nome, String idade, String cpf, String rg, String data_nasc, String sexo, String signo, String mae, String pai, String email, String senha, String cep, String endereco, String numero, String bairro, String cidade, String estado, String telefone_fixo, String celular, String altura, String peso, String tipo_sanguineo, String cor) {
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
        this.rg = rg;
        this.data_nasc = data_nasc;
        this.sexo = sexo;
        this.signo = signo;
        this.mae = mae;
        this.pai = pai;
        this.email = email;
        this.senha = senha;
        this.cep = cep;
        this.endereco = endereco;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.telefone_fixo = telefone_fixo;
        this.celular = celular;
        this.altura = altura;
        this.peso = peso;
        this.tipo_sanguineo = tipo_sanguineo;
        this.cor = cor;
    }
}
