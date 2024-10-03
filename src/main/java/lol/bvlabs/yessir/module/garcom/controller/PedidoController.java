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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import lol.bvlabs.yessir.module.garcom.domain.pedido.DadosAtualizacaoPedido;
import lol.bvlabs.yessir.module.garcom.domain.pedido.DadosCadastroPedido;
import lol.bvlabs.yessir.module.garcom.domain.pedido.DadosListagemPedido;
import lol.bvlabs.yessir.module.garcom.domain.pedido.Pedido;
import lol.bvlabs.yessir.module.garcom.domain.pedido.PedidoRepository;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
	
	@Autowired
	PedidoRepository pedidoRepository;

	@GetMapping
	public ResponseEntity<Page<DadosListagemPedido>> getAll(@PageableDefault(size = 20, sort = {"id"}) Pageable paginacao) {
		return ResponseEntity.ok(pedidoRepository.findAllByAtivoTrue(paginacao).map(DadosListagemPedido::new));
	}

	@GetMapping("/{id}")
	public ResponseEntity<DadosListagemPedido> getAllByEstabelecimentoId(@PageableDefault(size = 10) Pageable paginacao,
			@PathVariable Long id) {
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		if (pedido.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(new DadosListagemPedido(pedido.get()));
	}

	@GetMapping("/atendimento/{id}")
	public ResponseEntity<List<DadosListagemPedido>> getAllByMesaId(@PathVariable Long id) {
		List<Pedido> pedidoList = pedidoRepository.findAllAtivoByAtendimentoId(id);
		if (pedidoList.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		//Pedido pedidoMaisRecente = pedidoList.stream().max((a, p) -> a.getId().compareTo(p.getId())).get();
		return ResponseEntity.ok(pedidoList.stream().map(DadosListagemPedido::new).toList());
	}

	@PostMapping
	@Transactional
	public ResponseEntity<Pedido> post(@RequestBody DadosCadastroPedido dadosCadastroPedido, UriComponentsBuilder uriBuilder) {
		var pedido = pedidoRepository.save(new Pedido(dadosCadastroPedido));
		URI uri = uriBuilder.path("/atendimentos/{id}").buildAndExpand(pedido.getId()).toUri();
		return ResponseEntity.created(uri).body(pedido);
	}

	@PutMapping
	@Transactional
	public void put(@RequestBody DadosAtualizacaoPedido dadosAtualizacaoPedido) {
		var pedido = pedidoRepository.getReferenceById(dadosAtualizacaoPedido.id());
		pedido.atualizarInformacoes(dadosAtualizacaoPedido);
	}

	@DeleteMapping("/{id}")
	@Transactional
	public void delete(@PathVariable Long id) {
		var pedido = pedidoRepository.getReferenceById(id);
		pedido.excluir();
	}
}
