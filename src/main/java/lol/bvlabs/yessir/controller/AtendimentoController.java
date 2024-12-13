package lol.bvlabs.yessir.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
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
import lol.bvlabs.yessir.domain.atendimento.Atendimento;
import lol.bvlabs.yessir.domain.atendimento.AtendimentoRepository;
import lol.bvlabs.yessir.domain.atendimento.DadosAtualizacaoAtendimento;
import lol.bvlabs.yessir.domain.atendimento.DadosCadastroAtendimento;
import lol.bvlabs.yessir.domain.atendimento.DadosListagemAtendimento;
import lol.bvlabs.yessir.domain.mesa.DadosListagemMesa;
import lol.bvlabs.yessir.domain.mesa.MesaRepository;

@RestController
@RequestMapping("/atendimentos")
@SecurityRequirement(name = "bearer-key")
public class AtendimentoController {
	
	@Autowired
	AtendimentoRepository atendimentoRepository;
	
	@Autowired
	MesaRepository mesaRepository;

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
	public ResponseEntity<DadosListagemAtendimento> getOneByMesaId(@PageableDefault(size = 10) Pageable paginacao,
			@PathVariable Long id) {
		var atendimento = atendimentoRepository.findOneAtivoByMesaId(id);
		if (atendimento.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		//Atendimento atendimentoMaisRecente = atendimento.stream().max((a, p) -> a.getId().compareTo(p.getId())).get();
		return ResponseEntity.ok(new DadosListagemAtendimento(atendimento.get()));
	}

	@PostMapping
	@Transactional
	public ResponseEntity<DadosListagemAtendimento> post(@RequestBody @Valid DadosCadastroAtendimento dadosCadastroAtendimento, UriComponentsBuilder uriBuilder) {
		if (dadosCadastroAtendimento == null || dadosCadastroAtendimento.mesa() == null || dadosCadastroAtendimento.mesa().id() == null) {
			return ResponseEntity.of(ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Id de Mesa inválido.")).build();
		}
		var mesa = mesaRepository.findById(dadosCadastroAtendimento.mesa().id());
		if (mesa.isEmpty()) {
			return ResponseEntity.of(ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Mesa não existe.")).build();
		}
		var atendimentoAtivoListByMesaId = atendimentoRepository.findAllAtivosByMesaId(mesa.get().getId());
		if (!atendimentoAtivoListByMesaId.isEmpty()) {
			return ResponseEntity.of(ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, "Já existe atendimentos em espera para essa Mesa.")).build();
		}
		var atendimento = atendimentoRepository.save(new Atendimento(dadosCadastroAtendimento));
		var dadosListagemMesa = new DadosListagemMesa(mesa.get());
		var dadosListagemAtendimento = new DadosListagemAtendimento(atendimento.getId(), dadosListagemMesa, null, atendimento.getStatus());
		URI uri = uriBuilder.path("/atendimentos/{id}").buildAndExpand(dadosListagemAtendimento.id()).toUri();
		return ResponseEntity.created(uri).body(dadosListagemAtendimento);
	}

	@PutMapping
	@Transactional
	public ResponseEntity<DadosListagemAtendimento> put(@RequestBody DadosAtualizacaoAtendimento dadosAtualizacaoAtendimento) {
		if (dadosAtualizacaoAtendimento == null) {
			return ResponseEntity.of(ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Sem informações de Atendimento.")).build();
		}
		var atendimento = atendimentoRepository.getReferenceById(dadosAtualizacaoAtendimento.id());
		atendimento.atualizarInformacoes(dadosAtualizacaoAtendimento);
		return ResponseEntity.ok(new DadosListagemAtendimento(atendimento));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Long id) {
		var atendimento = atendimentoRepository.getReferenceById(id);
		atendimento.excluir();
		return ResponseEntity.noContent().build();
	}
}
