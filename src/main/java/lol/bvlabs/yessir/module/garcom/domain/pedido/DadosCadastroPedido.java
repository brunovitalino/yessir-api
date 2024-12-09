package lol.bvlabs.yessir.module.garcom.domain.pedido;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lol.bvlabs.yessir.module.garcom.domain.atendimento.DadosListagemAtendimento;
import lol.bvlabs.yessir.module.garcom.domain.cardapio.DadosListagemCardapio;

public record DadosCadastroPedido(
		@NotNull @Valid
		DadosListagemAtendimento atendimento,
		@NotNull @Valid
		DadosListagemCardapio cardapio,
		@NotNull
		Integer quantidade
) {}
