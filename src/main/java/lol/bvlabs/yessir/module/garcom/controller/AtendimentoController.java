package lol.bvlabs.yessir.module.garcom.controller;

import java.net.URI;
import java.util.List;
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

import lol.bvlabs.yessir.module.garcom.domain.atendimento.Atendimento;
import lol.bvlabs.yessir.module.garcom.domain.atendimento.AtendimentoRepository;
import lol.bvlabs.yessir.module.garcom.domain.atendimento.DadosAtualizacaoAtendimento;
import lol.bvlabs.yessir.module.garcom.domain.atendimento.DadosCadastroAtendimento;
import lol.bvlabs.yessir.module.garcom.domain.atendimento.DadosListagemAtendimento;

@RestController
@RequestMapping("/atendimentos")
public class AtendimentoController {
	
	@Autowired
	AtendimentoRepository atendimentoRepository;

	@GetMapping
	public ResponseEntity<Page<DadosListagemAtendimento>> getAll(@PageableDefault(size = 20) Pageable paginacao,
			@RequestParam(required = false) String nomeAtendente) {
		if (nomeAtendente != null) {
			return ResponseEntity.ok(atendimentoRepository.findByAtendenteNome(paginacao, nomeAtendente).map(DadosListagemAtendimento::new));
		}
		return ResponseEntity.ok(atendimentoRepository.findAllByAtivoTrue(paginacao).map(DadosListagemAtendimento::new));
	}

	@GetMapping("/{id}")
	public ResponseEntity<DadosListagemAtendimento> getAllByEstabelecimentoId(@PageableDefault(size = 10) Pageable paginacao,
			@PathVariable Long id) {
		Optional<Atendimento> atendimento = atendimentoRepository.findById(id);
		if (atendimento.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(new DadosListagemAtendimento(atendimento.get()));
	}

	@GetMapping("/mesa/{id}")
	public ResponseEntity<DadosListagemAtendimento> getAllByMesaId(@PageableDefault(size = 10) Pageable paginacao,
			@PathVariable Long id) {
		List<Atendimento> atendimentoList = atendimentoRepository.findAllAtivoByMesaId(id);
		if (atendimentoList.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Atendimento atendimentoMaisRecente = atendimentoList.stream().max((a, p) -> a.getId().compareTo(p.getId())).get();
		return ResponseEntity.ok(new DadosListagemAtendimento(atendimentoMaisRecente));
	}

	@PostMapping
	@Transactional
	public ResponseEntity<Atendimento> post(@RequestBody DadosCadastroAtendimento dadosCadastroAtendimento, UriComponentsBuilder uriBuilder) {
		var atendimento = atendimentoRepository.save(new Atendimento(dadosCadastroAtendimento));
		URI uri = uriBuilder.path("/atendimentos/{id}").buildAndExpand(atendimento.getId()).toUri();
		return ResponseEntity.created(uri).body(atendimento);
	}

	@PutMapping
	@Transactional
	public void put(@RequestBody DadosAtualizacaoAtendimento dadosAtualizacaoAtendimento) {
		var atendimento = atendimentoRepository.getReferenceById(dadosAtualizacaoAtendimento.id());
		atendimento.atualizarInformacoes(dadosAtualizacaoAtendimento);
	}

	@DeleteMapping("/{id}")
	@Transactional
	public void delete(@PathVariable Long id) {
		var atendimento = atendimentoRepository.getReferenceById(id);
		atendimento.excluir();
	}
}
