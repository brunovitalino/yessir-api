package lol.bvlabs.yessir.domain.mesa;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public record MesaOne(
		Long id,
		String nome
) {
	public MesaOne(Mesa mesa) {
		this(mesa.getId(), mesa.getNome());
	}
}
