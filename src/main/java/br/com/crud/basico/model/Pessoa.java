package br.com.crud.basico.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pessoa {

    private Long id;
    private String nome;
    private String cpf;
    private String dataNascimento;

    public Pessoa () {}
    public Pessoa(Long id, String nome, String cpf, String dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    public Pessoa(String nome, String cpf, String dataNascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", dataNascimento='" + dataNascimento + '\'' +
                '}';
    }
}
