package lol.bvlabs.yessir.module.garcom.controller;

import java.util.Optional;

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

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lol.bvlabs.yessir.module.garcom.domain.atendente.Atendente;
import lol.bvlabs.yessir.module.garcom.domain.atendente.AtendenteRepository;
import lol.bvlabs.yessir.module.garcom.domain.atendente.DadosAtualizacaoAtendente;
import lol.bvlabs.yessir.module.garcom.domain.atendente.DadosCadastroAtendente;
import lol.bvlabs.yessir.module.garcom.domain.atendente.DadosListagemAtendente;

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
		Optional<Atendente> atendente = atendenteRepository.findById(id);
		if (atendente.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(new DadosListagemAtendente(atendente.get()));
	}

	@PostMapping
	@Transactional
	public void post(@RequestBody @Valid DadosCadastroAtendente dadosCadastroAtendente) {
		atendenteRepository.save(new Atendente(dadosCadastroAtendente));
	}

	@PutMapping
	@Transactional
	public void put(@RequestBody DadosAtualizacaoAtendente dadosAtualizacaoAtendente) {
		var mesa = atendenteRepository.getReferenceById(dadosAtualizacaoAtendente.id());
		mesa.atualizarInformacoes(dadosAtualizacaoAtendente);
	}

	@DeleteMapping("/{id}")
	@Transactional
	public void delete(@PathVariable Long id) {
		var mesa = atendenteRepository.getReferenceById(id);
		mesa.excluir();
	}
}
