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

import lol.bvlabs.yessir.module.garcom.domain.mesa.DadosAtualizacaoMesa;
import lol.bvlabs.yessir.module.garcom.domain.mesa.DadosCadastroMesa;
import lol.bvlabs.yessir.module.garcom.domain.mesa.DadosListagemMesa;
import lol.bvlabs.yessir.module.garcom.domain.mesa.Mesa;
import lol.bvlabs.yessir.module.garcom.domain.mesa.MesaRepository;

@RestController
@RequestMapping("/mesas")
public class MesaController {
	
	@Autowired
	MesaRepository mesaRepository;

	@GetMapping
	public ResponseEntity<Page<DadosListagemMesa>> getAll(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao,
			@RequestParam(required = false) String nome) {
		if (nome != null) {
			return ResponseEntity.ok(mesaRepository.findByNome(paginacao, nome).map(DadosListagemMesa::new));
		}
		return ResponseEntity.ok(mesaRepository.findAllByAtivoTrue(paginacao).map(DadosListagemMesa::new));
	}

	@GetMapping("/{id}")
	public ResponseEntity<DadosListagemMesa> getOneById(@PathVariable Long id) {
		Optional<Mesa> mesa = mesaRepository.findById(id);
		if (mesa.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(new DadosListagemMesa(mesa.get()));
	}

	@PostMapping
	@Transactional
	public void post(@RequestBody DadosCadastroMesa dadosCadastroMesa) {
		mesaRepository.save(new Mesa(dadosCadastroMesa));
	}

	@PutMapping
	@Transactional
	public void put(@RequestBody DadosAtualizacaoMesa dadosAtualizacaoMesa) {
		var mesa = mesaRepository.getReferenceById(dadosAtualizacaoMesa.id());
		mesa.atualizarInformacoes(dadosAtualizacaoMesa);
	}

	@DeleteMapping("/{id}")
	@Transactional
	public void delete(@PathVariable Long id) {
		var mesa = mesaRepository.getReferenceById(id);
		mesa.excluir();
	}
}
