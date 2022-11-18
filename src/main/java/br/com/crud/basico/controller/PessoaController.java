package br.com.crud.basico.controller;

import br.com.crud.basico.model.Pessoa;
import br.com.crud.basico.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class PessoaController {

    @Autowired
    PessoaRepository pessoaRepository;

    @GetMapping("/pessoas")
    public ResponseEntity<List<Pessoa>> getAllPessoa(@RequestParam(required = false) String nome) {

        try {
            List<Pessoa> pessoaList = new ArrayList<>();

            if (nome == null) {
                pessoaRepository.findAll().forEach(pessoaList::add);
            } else {
                pessoaRepository.findByNomeContaining(nome).forEach(pessoaList::add);
            }

            if (pessoaList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(pessoaList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pessoa/{id}")
    public ResponseEntity<Pessoa> getPessoaById(@PathVariable("id") Long id) {
        Pessoa pessoa = pessoaRepository.findById(id);

        if (pessoa == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(pessoa, HttpStatus.OK);
    }

    @PostMapping("/pessoa")
    public ResponseEntity<String> createPessoa(@RequestBody Pessoa pessoa) {
        try {
            pessoaRepository.save(new Pessoa(pessoa.getId(), pessoa.getNome(), pessoa.getCpf(), pessoa.getDataNascimento()));
            return new ResponseEntity<>("Uma nova pessoa foi salva com sucesso", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/pessoa/{id}")
    public ResponseEntity<String> updatePessoa(@PathVariable("id") Long id, @RequestBody Pessoa pessoa) {
        Pessoa pessoaUpdate = pessoaRepository.findById(id);
        if (pessoaUpdate == null) {
            return new ResponseEntity<>("Nao foi possivel encontrar essa pessoa id=" + id, HttpStatus.NOT_FOUND);
        } else {
            pessoaUpdate.setId(id);
            pessoaUpdate.setNome(pessoa.getNome());
            pessoaUpdate.setCpf(pessoa.getCpf());
            pessoaUpdate.setDataNascimento(pessoa.getDataNascimento());

            pessoaRepository.update(pessoaUpdate);
            return new ResponseEntity<>("Atualizado com sucesso", HttpStatus.OK);
        }
    }

    @DeleteMapping("/pessoa/{id}")
    public ResponseEntity<String> deletePessoa(@PathVariable("id") Long id) {
        try {
            int result = pessoaRepository.deleteById(id);
            if (result == 0) {
                return new ResponseEntity<>("Nao foi possivel achar essa pessoa id=" + id, HttpStatus.OK);
            }
            return new ResponseEntity<>("Pessoa deletada com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Nao foi possivel deletar", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/pessoas")
    public ResponseEntity<String> deleteAllPessoas() {
        try {
            int dados = pessoaRepository.deleteAll();
            return new ResponseEntity<>("Deletados " + dados + " pessoas com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Nao foi possivel deletar todas pessoas", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
