package br.com.vainaweb.escolat3.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vainaweb.escola.t3.dto.DadosColaborador;
import br.com.vainaweb.escolat3.dto.DadosAtualizados;
import br.com.vainaweb.escolat3.model.ColaboradorModel;
import br.com.vainaweb.escolat3.repository.ColaboradorRepository;
import br.com.vainaweb.escolat3.service.ColaboradorService;

@RestController
@RequestMapping("/colaborador-teste")
public class ColaboradorController {
	
	@Autowired //retorna o controle para o spring container
	private ColaboradorService service;
	
	@Autowired
	private ColaboradorRepository repository;
	
	@GetMapping
	public List<ColaboradorModel> encontrarColaboradores() {
		return service.encontrarTodosOsColaboradores();
	}
	
	@GetMapping
	public Optional<ColaboradorModel> encontrarUmColaboradorPorId(@PathVariable Long id) {
		return service.encontrarUmColaborador(id);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ColaboradorModel> listarPorId(@PathVariable Long id) {
		var response = repository.findById(id).map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
		return response;
	}
	
	@PostMapping //Verbo HTTP POST
	public ResponseEntity<String> cadastrarColaborador(@RequestBody DadosColaborador dados) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrar(dados));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizados dados) {
		var colaborador = repository.getReferenceById(id);
		colaborador.save(colaborador);
		return ResponseEntity.status(HttpStatus.OK).body("Dados Atualizados");
	}
	
	@DeleteMapping("/{id}")
	public String deletar(@PathVariable Long id) {
		repository.deleteById(id);
		return "Deletado com Sucesso";
	}
}
