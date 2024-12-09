package lol.bvlabs.yessir.module.garcom.domain.mesa;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroMesa(
		@NotBlank
		String nome
) {}
