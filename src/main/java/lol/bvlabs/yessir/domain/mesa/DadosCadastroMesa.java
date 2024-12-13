package lol.bvlabs.yessir.domain.mesa;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroMesa(
		@NotBlank
		String nome
) {}
