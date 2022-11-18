package br.com.crud.basico.repository;

import br.com.crud.basico.model.Pessoa;

import java.util.List;

public interface PessoaRepository {
    int save(Pessoa pessoa);
    int update(Pessoa pessoa);
    Pessoa findById(Long id);
    List<Pessoa> findAll();
    List<Pessoa> findByNomeContaining(String nome);
    int deleteById(Long id);
    int deleteAll();
}
