package lol.bvlabs.yessir.module.garcom.domain.mesa;

import jakarta.validation.constraints.NotNull;

public record DadosListagemMesa(
		@NotNull
		Long id,
		String nome
) {
	
	public DadosListagemMesa(Mesa mesa) {
		this(mesa.getId(), mesa.getNome());
	}
}
