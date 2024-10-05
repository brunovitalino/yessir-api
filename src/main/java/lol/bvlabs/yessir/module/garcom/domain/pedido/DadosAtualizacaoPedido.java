package lol.bvlabs.yessir.module.garcom.domain.pedido;

import lol.bvlabs.yessir.module.garcom.domain.atendimento.DadosListagemAtendimento;
import lol.bvlabs.yessir.module.garcom.domain.cardapio.DadosListagemCardapio;

public record DadosAtualizacaoPedido(
	Long id,
	DadosListagemAtendimento atendimento,
	DadosListagemCardapio cardapio,
	Integer quantidade
) {}
