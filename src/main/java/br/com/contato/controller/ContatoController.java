package br.com.contato.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.contato.model.Contato;
import br.com.contato.repository.ContatoRepository;

@RestController
@RequestMapping("/contatos")
public class ContatoController {
	
	@Autowired
	private ContatoRepository repository;
	
	@PostMapping
	public Contato novo(@Valid @RequestBody Contato contato){
		return repository.save(contato);
	}
	
	@GetMapping
	public List<Contato> consultar(){
		return repository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Contato> buscarPorId(@PathVariable Long id){
		Optional<Contato> contato = repository.findById(id);
		if(!contato.isPresent()){
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(contato.get());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Contato> atualizar(@PathVariable Long id, @Valid @RequestBody Contato contato){
		Optional<Contato> contatoPesquisa = repository.findById(id);
		if(!contatoPesquisa.isPresent()){
			return ResponseEntity.notFound().build();
		}
		Contato contatoAtualizar = contatoPesquisa.get();
		BeanUtils.copyProperties(contato, contatoAtualizar, "id");
		contatoAtualizar = repository.save(contatoAtualizar);
		return ResponseEntity.ok(contatoAtualizar);	
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id){
		Optional<Contato> contatoPesquisa = repository.findById(id);
		if(!contatoPesquisa.isPresent()){
			return ResponseEntity.notFound().build();
		}
		Contato contatoExcluir = contatoPesquisa.get();
		repository.delete(contatoExcluir);
		return ResponseEntity.noContent().build();
	}
}
