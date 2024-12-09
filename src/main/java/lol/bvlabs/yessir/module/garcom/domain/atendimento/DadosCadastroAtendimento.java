package lol.bvlabs.yessir.module.garcom.domain.atendimento;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lol.bvlabs.yessir.module.garcom.domain.atendente.DadosListagemAtendente;
import lol.bvlabs.yessir.module.garcom.domain.mesa.DadosListagemMesa;

public record DadosCadastroAtendimento(
		@NotNull @Valid
		DadosListagemMesa mesa,
		DadosListagemAtendente atendente
) {}
