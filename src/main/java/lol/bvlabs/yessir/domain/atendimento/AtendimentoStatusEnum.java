package lol.bvlabs.yessir.domain.atendimento;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AtendimentoStatusEnum {
	AGUARDANDO("AGUARDANDO"), EM_ATENDIMENTO("EM_ATENDIMENTO"), EM_PAGAMENTO("EM_PAGAMENTO"), ENCERRADO("ENCERRADO");

	private String value;
}
