package lol.bvlabs.yessir.module.garcom.controller;

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
import lol.bvlabs.yessir.module.garcom.domain.estabelecimento.DadosAtualizacaoEstabelecimento;
import lol.bvlabs.yessir.module.garcom.domain.estabelecimento.DadosCadastroEstabelecimento;
import lol.bvlabs.yessir.module.garcom.domain.estabelecimento.DadosListagemEstabelecimento;
import lol.bvlabs.yessir.module.garcom.domain.estabelecimento.Estabelecimento;
import lol.bvlabs.yessir.module.garcom.domain.estabelecimento.EstabelecimentoRepository;

@RestController
@RequestMapping("/estabelecimentos")
@SecurityRequirement(name = "bearer-key")
public class EstabelecimentoController {
	
	@Autowired
	EstabelecimentoRepository estabelecimentoRepository;

	@GetMapping
	public ResponseEntity<Page<DadosListagemEstabelecimento>> getAll(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao,
			@RequestParam(required = false) String nome) {
		if (nome != null) {
			return ResponseEntity.ok(estabelecimentoRepository.findByNome(paginacao, nome).map(DadosListagemEstabelecimento::new));
		}
		return ResponseEntity.ok(estabelecimentoRepository.findAllByAtivoTrue(paginacao).map(DadosListagemEstabelecimento::new));
	}

	@PostMapping
	@Transactional
	public void post(@RequestBody @Valid DadosCadastroEstabelecimento dadosCadastroEstabelecimento) {
		estabelecimentoRepository.save(new Estabelecimento(dadosCadastroEstabelecimento));
	}

	@PutMapping
	@Transactional
	public void put(@RequestBody DadosAtualizacaoEstabelecimento dadosAtualizacaoEstabelecimento) {
		var estabelecimento = estabelecimentoRepository.getReferenceById(dadosAtualizacaoEstabelecimento.id());
		estabelecimento.atualizarInformacoes(dadosAtualizacaoEstabelecimento);
	}

	@DeleteMapping("/{id}")
	@Transactional
	public void delete(@PathVariable Long id) {
		var estabelecimento = estabelecimentoRepository.getReferenceById(id);
		estabelecimento.excluir();
	}
}
