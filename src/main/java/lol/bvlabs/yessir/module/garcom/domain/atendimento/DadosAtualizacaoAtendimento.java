package lol.bvlabs.yessir.module.garcom.domain.atendimento;

import lol.bvlabs.yessir.module.garcom.domain.atendente.DadosAtualizacaoAtendente;
import lol.bvlabs.yessir.module.garcom.domain.mesa.DadosAtualizacaoMesa;

public record DadosAtualizacaoAtendimento(
	Long id,
	DadosAtualizacaoMesa mesa,
	DadosAtualizacaoAtendente atendente,
	AtendimentoStatusEnum status
) {}
