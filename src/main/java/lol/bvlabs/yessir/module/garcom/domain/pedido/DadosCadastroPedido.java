package lol.bvlabs.yessir.module.garcom.domain.pedido;

import lol.bvlabs.yessir.module.garcom.domain.atendimento.DadosCadastroAtendimento;
import lol.bvlabs.yessir.module.garcom.domain.cardapio.DadosCadastroCardapio;

public record DadosCadastroPedido(
		Long id,
		DadosCadastroAtendimento atendimento,
		DadosCadastroCardapio cardapio,
		Integer quantidade
) {}
