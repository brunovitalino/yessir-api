package lol.bvlabs.yessir.module.garcom.domain.atendimento;

public record DadosCadastroAtendimento(
		Long id,
		Long mesaId,
		Long atendenteId,
		AtendimentoStatus status
) {}
