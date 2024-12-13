package lol.bvlabs.yessir.domain.pedido;

import lol.bvlabs.yessir.domain.atendimento.DadosListagemAtendimento;
import lol.bvlabs.yessir.domain.cardapio.DadosListagemCardapio;

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
