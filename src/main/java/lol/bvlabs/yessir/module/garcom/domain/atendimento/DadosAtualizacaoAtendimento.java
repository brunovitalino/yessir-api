package lol.bvlabs.yessir.module.garcom.domain.atendimento;

public record DadosAtualizacaoAtendimento(
	Long id,
	Long mesaId,
	Long atendenteId,
	AtendimentoStatus status
) {}
