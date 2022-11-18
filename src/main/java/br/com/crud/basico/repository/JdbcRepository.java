package br.com.crud.basico.repository;

import br.com.crud.basico.model.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcRepository implements PessoaRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public int save(Pessoa pessoa) {
        return jdbcTemplate.update("INSERT INTO pessoa (nome, cpf, data_nascimento) VALUES(?,?,?)",
        new Object[] {pessoa.getNome(), pessoa.getCpf(), pessoa.getDataNascimento() });
    }

    @Override
    public int update(Pessoa pessoa) {
        return jdbcTemplate.update("UPDATE pessoa SET nome=?, cpf=?, data_nascimento=?, WHERE id=?",
        new Object[]{pessoa.getNome(), pessoa.getCpf(), pessoa.getDataNascimento(), pessoa.getId() });
    }

    @Override
    public Pessoa findById(Long id) {
        try {
            Pessoa pessoa = jdbcTemplate.queryForObject("SELECT * FROM pessoa WHERE id=?",
                    BeanPropertyRowMapper.newInstance(Pessoa.class), id);
            return pessoa;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Pessoa> findAll() {
        return jdbcTemplate.query("SELECT * from pessoa", BeanPropertyRowMapper.newInstance(Pessoa.class));
    }

    @Override
    public List<Pessoa> findByNomeContaining(String nome) {
        String query = "SELECT * from pessoa WHERE nome LIKE '%" + nome + "%'";
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(Pessoa.class));
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM pessoa WHERE id=?", id);
    }

    @Override
    public int deleteAll() {
        return jdbcTemplate.update("DELETE from pessoa");
    }
}
