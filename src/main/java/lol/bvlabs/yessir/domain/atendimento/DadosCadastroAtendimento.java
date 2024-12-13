package lol.bvlabs.yessir.domain.atendimento;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lol.bvlabs.yessir.domain.atendente.DadosListagemAtendente;
import lol.bvlabs.yessir.domain.mesa.DadosListagemMesa;

public record DadosCadastroAtendimento(
		@NotNull @Valid
		DadosListagemMesa mesa,
		DadosListagemAtendente atendente
) {}
