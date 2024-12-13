package lol.bvlabs.yessir.domain.atendimento;

import lol.bvlabs.yessir.domain.atendente.DadosAtendente;
import lol.bvlabs.yessir.domain.mesa.DadosMesa;

public record DadosAtendimento(
		Long id,
		DadosMesa mesa,
		DadosAtendente atendente,
		AtendimentoStatusEnum status
) {}
