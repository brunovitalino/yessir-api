package lol.bvlabs.yessir.domain.pedido;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lol.bvlabs.yessir.domain.atendimento.DadosListagemAtendimento;
import lol.bvlabs.yessir.domain.cardapio.DadosListagemCardapio;

public record DadosCadastroPedido(
		@NotNull @Valid
		DadosListagemAtendimento atendimento,
		@NotNull @Valid
		DadosListagemCardapio cardapio,
		@NotNull
		Integer quantidade
) {}
