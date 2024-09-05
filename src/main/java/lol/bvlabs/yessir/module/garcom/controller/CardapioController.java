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

import lol.bvlabs.yessir.module.garcom.domain.cardapio.Cardapio;
import lol.bvlabs.yessir.module.garcom.domain.cardapio.CardapioRepository;
import lol.bvlabs.yessir.module.garcom.domain.cardapio.DadosAtualizacaoCardapio;
import lol.bvlabs.yessir.module.garcom.domain.cardapio.DadosCadastroCardapio;
import lol.bvlabs.yessir.module.garcom.domain.cardapio.DadosListagemCardapio;

@RestController
@RequestMapping("/cardapios")
public class CardapioController {
	
	@Autowired
	CardapioRepository cardapioRepository;

	@GetMapping
	public ResponseEntity<Page<DadosListagemCardapio>> getAll(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao,
			@RequestParam(required = false) String nome) {
		if (nome != null) {
			return ResponseEntity.ok(cardapioRepository.findByNome(paginacao, nome).map(DadosListagemCardapio::new));
		}
		return ResponseEntity.ok(cardapioRepository.findAllByAtivoTrue(paginacao).map(DadosListagemCardapio::new));
	}

	@GetMapping("/estabelecimentos/{estabelecimentoId}")
	public ResponseEntity<Page<DadosListagemCardapio>> getAllByEstabelecimentoId(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao,
			@PathVariable Long estabelecimentoId) {
		return ResponseEntity.ok(cardapioRepository.findByEstabelecimentoId(paginacao, estabelecimentoId).map(DadosListagemCardapio::new));
	}

	@PostMapping
	@Transactional
	public void post(@RequestBody DadosCadastroCardapio dadosCadastroCardapio) {
		cardapioRepository.save(new Cardapio(dadosCadastroCardapio));
	}

	@PutMapping
	@Transactional
	public void put(@RequestBody DadosAtualizacaoCardapio dadosAtualizacaoCardapio) {
		var cardapio = cardapioRepository.getReferenceById(dadosAtualizacaoCardapio.id());
		cardapio.atualizarInformacoes(dadosAtualizacaoCardapio);
	}

	@DeleteMapping("/{id}")
	@Transactional
	public void delete(@PathVariable Long id) {
		var cardapio = cardapioRepository.getReferenceById(id);
		cardapio.excluir();
	}
}
