package lol.bvlabs.yessir.module.garcom.domain.atendimento;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AtendimentoStatusEnum {
	AGUARDANDO("AGUARDANDO"), EM_ATENDIMENTO("EM_ATENDIMENTO"), ENCERRADO("ENCERRADO");

	private String value;
}
