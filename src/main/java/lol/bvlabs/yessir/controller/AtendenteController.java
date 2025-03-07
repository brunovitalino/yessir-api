package lol.bvlabs.yessir.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lol.bvlabs.yessir.domain.atendente.Atendente;
import lol.bvlabs.yessir.domain.atendente.AtendenteRepository;
import lol.bvlabs.yessir.domain.atendente.DadosAtualizacaoAtendente;
import lol.bvlabs.yessir.domain.atendente.DadosCadastroAtendente;
import lol.bvlabs.yessir.domain.atendente.DadosListagemAtendente;

@RestController
@RequestMapping("/atendentes")
@SecurityRequirement(name = "bearer-key")
public class AtendenteController {
	
	@Autowired
	AtendenteRepository atendenteRepository;

	@GetMapping
	public ResponseEntity<Page<DadosListagemAtendente>> getAll(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao,
			@RequestParam(required = false) String nome) {
		if (nome != null) {
			return ResponseEntity.ok(atendenteRepository.findByNome(paginacao, nome).map(DadosListagemAtendente::new));
		}
		return ResponseEntity.ok(atendenteRepository.findAllByAtivoTrue(paginacao).map(DadosListagemAtendente::new));
	}

	@GetMapping("/{id}")
	public ResponseEntity<DadosListagemAtendente> getOneById(@PathVariable Long id) {
		var atendente = atendenteRepository.getReferenceById(id);
		return ResponseEntity.ok(new DadosListagemAtendente(atendente));
	}

	@PostMapping
	@Transactional
	public ResponseEntity<DadosListagemAtendente> post(@RequestBody @Valid DadosCadastroAtendente dadosCadastroAtendente, UriComponentsBuilder uriBuilder) {
		var atendente = atendenteRepository.save(new Atendente(dadosCadastroAtendente));
		URI uri = uriBuilder.path("/atendentes/{id}").buildAndExpand(atendente.getId()).toUri();
		return ResponseEntity.created(uri).body(new DadosListagemAtendente(atendente));
	}

	@PutMapping
	@Transactional
	public ResponseEntity<DadosListagemAtendente> put(@RequestBody DadosAtualizacaoAtendente dadosAtualizacaoAtendente) {
		var atendente = atendenteRepository.getReferenceById(dadosAtualizacaoAtendente.id());
		atendente.atualizarInformacoes(dadosAtualizacaoAtendente);
		return ResponseEntity.ok(new DadosListagemAtendente(atendente));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Long id) {
		var mesa = atendenteRepository.getReferenceById(id);
		mesa.excluir();
		return ResponseEntity.noContent().build();
	}
}
