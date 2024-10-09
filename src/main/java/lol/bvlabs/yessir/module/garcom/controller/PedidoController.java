package lol.bvlabs.yessir.module.garcom.controller;

import java.net.URI;
import java.util.List;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lol.bvlabs.yessir.module.garcom.domain.atendimento.AtendimentoRepository;
import lol.bvlabs.yessir.module.garcom.domain.atendimento.AtendimentoStatusEnum;
import lol.bvlabs.yessir.module.garcom.domain.cardapio.CardapioRepository;
import lol.bvlabs.yessir.module.garcom.domain.pedido.DadosAtualizacaoPedido;
import lol.bvlabs.yessir.module.garcom.domain.pedido.DadosCadastroPedido;
import lol.bvlabs.yessir.module.garcom.domain.pedido.DadosListagemPedido;
import lol.bvlabs.yessir.module.garcom.domain.pedido.Pedido;
import lol.bvlabs.yessir.module.garcom.domain.pedido.PedidoRepository;

@RestController
@RequestMapping("/pedidos")
@SecurityRequirement(name = "bearer-key")
public class PedidoController {
	
	@Autowired
	PedidoRepository pedidoRepository;
	
	@Autowired
	AtendimentoRepository atendimentoRepository;
	
	@Autowired
	CardapioRepository cardapioRepository;

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

	@GetMapping("/atendimento/{atendimentoId}")
	public ResponseEntity<List<DadosListagemPedido>> getAllByMesaId(@PathVariable Long atendimentoId) {
		List<Pedido> pedidoList = pedidoRepository.findAllByAtendimentoId(atendimentoId);
		if (pedidoList.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(pedidoList.stream().map(DadosListagemPedido::new).toList());
	}

	@PostMapping
	@Transactional
	public ResponseEntity<DadosListagemPedido> post(@RequestBody DadosCadastroPedido dadosCadastroPedido, UriComponentsBuilder uriBuilder) {
		if (dadosCadastroPedido == null) {
			return ResponseEntity.of(ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Sem informações de Pedido.")).build();
		}
		if (dadosCadastroPedido.atendimento() == null || dadosCadastroPedido.atendimento().id() == null) {
			return ResponseEntity.of(ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Id de Atendimento inválido.")).build();
		}
		if (dadosCadastroPedido.cardapio() == null || dadosCadastroPedido.cardapio().id() == null) {
			return ResponseEntity.of(ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Id de Cardápio inválido.")).build();
		}
		if (dadosCadastroPedido.quantidade() == null || dadosCadastroPedido.quantidade() < 1) {
			return ResponseEntity.of(ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Quantidade inválido.")).build();
		}

		var atendimento = atendimentoRepository.findAtivoById(dadosCadastroPedido.atendimento().id());
		if (atendimento.isEmpty()) {
			return ResponseEntity.of(ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Atendimento não existe.")).build();
		}
		if (AtendimentoStatusEnum.ENCERRADO.equals(atendimento.get().getStatus())) {
			return ResponseEntity.of(ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Atendimento se encontra encerrado.")).build();
		}
		
		var cardapio = cardapioRepository.findById(dadosCadastroPedido.cardapio().id());
		if (cardapio.isEmpty()) {
			return ResponseEntity.of(ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Cardápio não existe.")).build();
		}
		
		var pedidoExistente = pedidoRepository.findAllAtivoByAtendimentoIdAndCardapioId(atendimento.get().getId(), cardapio.get().getId());
		if (!pedidoExistente.isEmpty()) {
			var dadosAtualizacaoPedido = new DadosAtualizacaoPedido(
					null, null, null, pedidoExistente.get().getQuantidade() + dadosCadastroPedido.quantidade()
			);
			pedidoExistente.get().atualizarInformacoes(dadosAtualizacaoPedido);
			var dadosListagemPedido = new DadosListagemPedido(pedidoExistente.get());
			URI uri = uriBuilder.path("/atendimentos/{id}").buildAndExpand(dadosListagemPedido.id()).toUri();
			return ResponseEntity.created(uri).body(dadosListagemPedido);
		}
		
		var pedido = pedidoRepository.save(new Pedido(dadosCadastroPedido));
		var dadosListagemPedido = new DadosListagemPedido(pedido);
		URI uri = uriBuilder.path("/atendimentos/{id}").buildAndExpand(dadosListagemPedido.id()).toUri();
		return ResponseEntity.created(uri).body(dadosListagemPedido);
	}

	@PutMapping
	@Transactional
	public ResponseEntity<DadosListagemPedido> put(@RequestBody DadosAtualizacaoPedido dadosAtualizacaoPedido) {
		if (dadosAtualizacaoPedido == null) {
			return ResponseEntity.of(ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Sem informações de Pedido.")).build();
		}
		var pedido = pedidoRepository.getReferenceById(dadosAtualizacaoPedido.id());
		pedido.atualizarInformacoes(dadosAtualizacaoPedido);
		return ResponseEntity.ok(new DadosListagemPedido(pedido));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Long id) {
		var pedido = pedidoRepository.getReferenceById(id);
		pedido.excluir();
		return ResponseEntity.ok().build();
	}
}
