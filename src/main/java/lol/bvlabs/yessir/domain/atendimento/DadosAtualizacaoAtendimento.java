package lol.bvlabs.yessir.domain.atendimento;

import lol.bvlabs.yessir.domain.atendente.DadosAtualizacaoAtendente;
import lol.bvlabs.yessir.domain.mesa.DadosAtualizacaoMesa;

public record DadosAtualizacaoAtendimento(
	Long id,
	DadosAtualizacaoMesa mesa,
	DadosAtualizacaoAtendente atendente,
	AtendimentoStatusEnum status
) {}
