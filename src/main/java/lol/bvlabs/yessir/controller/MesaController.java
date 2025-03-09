package lol.bvlabs.yessir.controller;

import java.net.URI;
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
import org.springframework.web.util.UriComponentsBuilder;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lol.bvlabs.yessir.domain.mesa.DadosAtualizacaoMesa;
import lol.bvlabs.yessir.domain.mesa.DadosCadastroMesa;
import lol.bvlabs.yessir.domain.mesa.DadosListagemMesa;
import lol.bvlabs.yessir.domain.mesa.Mesa;
import lol.bvlabs.yessir.domain.mesa.MesaOne;
import lol.bvlabs.yessir.domain.mesa.MesaRepository;
import lol.bvlabs.yessir.domain.mesa.MesaService;

@RestController
@RequestMapping("/mesas")
@SecurityRequirement(name = "bearer-key")
public class MesaController {

	@Autowired						// Remover
	MesaRepository mesaRepository;

	@Autowired
    private MesaService mesaService;

	@GetMapping("/{id}")
	public ResponseEntity<DadosListagemMesa> getOneById(@PathVariable Long id) {
		Optional<DadosListagemMesa> mesa = mesaService.getOneById(id);
		if (mesa.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(mesa.get());
	}

	@GetMapping
	public ResponseEntity<Page<DadosListagemMesa>> getAll(@PageableDefault(size = 20, sort = {"id"}) Pageable paginacao,
			@RequestParam(required = false) String nome) {
		if (nome != null) {
			return ResponseEntity.ok(mesaRepository.findByNome(paginacao, nome).map(DadosListagemMesa::new));
		}
		return ResponseEntity.ok(mesaRepository.findAllByAtivoTrue(paginacao).map(DadosListagemMesa::new));
	}

	@PostMapping
	@Transactional
	public ResponseEntity<DadosListagemMesa> post(@RequestBody @Valid DadosCadastroMesa dadosCadastroMesa, UriComponentsBuilder uriBuilder) {
		Mesa mesa = mesaRepository.save(new Mesa(dadosCadastroMesa));
		DadosListagemMesa dadosListagemMesa = new DadosListagemMesa(mesa);
		URI uri = uriBuilder.path("/mesas/{id}").buildAndExpand(dadosListagemMesa.id()).toUri();
		return ResponseEntity.created(uri).body(dadosListagemMesa);
	}

	@PutMapping
	@Transactional
	public ResponseEntity<DadosListagemMesa> put(@RequestBody DadosAtualizacaoMesa dadosAtualizacaoMesa) {
		var mesa = mesaRepository.getReferenceById(dadosAtualizacaoMesa.id());
		mesa.atualizarInformacoes(dadosAtualizacaoMesa);
		return ResponseEntity.ok(new DadosListagemMesa(mesa));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Long id) {
		var mesa = mesaRepository.getReferenceById(id);
		mesa.excluir();
		return ResponseEntity.noContent().build();
	}
}
