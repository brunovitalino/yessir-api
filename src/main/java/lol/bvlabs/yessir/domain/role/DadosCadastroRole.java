package lol.bvlabs.yessir.domain.role;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroRole(
		@NotBlank
		String nome
) {}
