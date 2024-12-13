package lol.bvlabs.yessir.domain.pedido;

import lol.bvlabs.yessir.domain.atendimento.DadosListagemAtendimento;
import lol.bvlabs.yessir.domain.cardapio.DadosListagemCardapio;

public record DadosAtualizacaoPedido(
	Long id,
	DadosListagemAtendimento atendimento,
	DadosListagemCardapio cardapio,
	Integer quantidade
) {}
