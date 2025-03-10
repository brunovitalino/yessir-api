package lol.bvlabs.yessir.domain.mesa;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import jakarta.validation.constraints.NotNull;

@JsonSerialize
public record DadosListagemMesa(
		@NotNull
		Long id,
		String nome
) {

	public DadosListagemMesa(Mesa mesa) {
		this(mesa.getId(), mesa.getNome());
	}

	public DadosListagemMesa(MesaPOJO mesaPOJO) {
		this(mesaPOJO.id(), mesaPOJO.nome());
	}
}
