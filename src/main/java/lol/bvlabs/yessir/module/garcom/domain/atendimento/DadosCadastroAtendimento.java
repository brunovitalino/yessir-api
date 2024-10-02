package lol.bvlabs.yessir.module.garcom.domain.atendimento;

import lol.bvlabs.yessir.module.garcom.domain.atendente.DadosCadastroAtendente;
import lol.bvlabs.yessir.module.garcom.domain.mesa.DadosCadastroMesa;

public record DadosCadastroAtendimento(
		Long id,
		DadosCadastroMesa mesa,
		DadosCadastroAtendente atendente,
		AtendimentoStatusEnum status
) {}
