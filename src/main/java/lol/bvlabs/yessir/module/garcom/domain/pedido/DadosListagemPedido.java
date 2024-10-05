package lol.bvlabs.yessir.module.garcom.domain.pedido;

import lol.bvlabs.yessir.module.garcom.domain.atendimento.DadosListagemAtendimento;
import lol.bvlabs.yessir.module.garcom.domain.cardapio.DadosListagemCardapio;

public record DadosListagemPedido(
		Long id,
		DadosListagemAtendimento atendimento,
		DadosListagemCardapio cardapio,
		Integer quantidade
) {
	
	public DadosListagemPedido(Pedido pedido) {
		this(
			pedido.getId(),
			pedido.getAtendimento() == null ? null : new DadosListagemAtendimento(pedido.getAtendimento()),
			pedido.getCardapio() == null ? null : new DadosListagemCardapio(pedido.getCardapio()),
			pedido.getQuantidade()
		);
	}
}
