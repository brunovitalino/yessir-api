package lol.bvlabs.yessir.domain.atendimento;

import jakarta.validation.constraints.NotNull;
import lol.bvlabs.yessir.domain.atendente.DadosListagemAtendente;
import lol.bvlabs.yessir.domain.mesa.DadosListagemMesa;

public record DadosListagemAtendimento(
		@NotNull
		Long id,
		DadosListagemMesa mesa,
		DadosListagemAtendente atendente,
		AtendimentoStatusEnum status
) {
	public DadosListagemAtendimento(Atendimento atendimento) {
		this(
			atendimento.getId(),
			atendimento.getMesa() == null ? null : new DadosListagemMesa(atendimento.getMesa()),
			atendimento.getAtendente() == null ? null : new DadosListagemAtendente(atendimento.getAtendente()),
			atendimento.getStatus()
		);
	}
}
