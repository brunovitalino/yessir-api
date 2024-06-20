package lol.bvlabs.yessir.module.garcom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lol.bvlabs.yessir.module.garcom.domain.estabelecimento.DadosCadastroEstabelecimento;
import lol.bvlabs.yessir.module.garcom.domain.estabelecimento.DadosListagemEstabelecimento;
import lol.bvlabs.yessir.module.garcom.domain.estabelecimento.Estabelecimento;
import lol.bvlabs.yessir.module.garcom.domain.estabelecimento.EstabelecimentoRepository;

@RestController
@RequestMapping("/estabelecimentos")
public class EstabelecimentoController {
	
	@Autowired
	EstabelecimentoRepository estabelecimentoRepository;

	@GetMapping
	public ResponseEntity<Page<DadosListagemEstabelecimento>> getAll(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao,
			@RequestParam String nome) {
		if (nome != null) {
			return ResponseEntity.ok(estabelecimentoRepository.findByNome(paginacao, nome).map(DadosListagemEstabelecimento::new));
		}
		return ResponseEntity.ok(estabelecimentoRepository.findAll(paginacao).map(DadosListagemEstabelecimento::new));
	}

	@PostMapping
	@Transactional
	public void post(@RequestBody DadosCadastroEstabelecimento dadosCadastroEstabelecimento) {
		estabelecimentoRepository.save(new Estabelecimento(dadosCadastroEstabelecimento));
	}
}
