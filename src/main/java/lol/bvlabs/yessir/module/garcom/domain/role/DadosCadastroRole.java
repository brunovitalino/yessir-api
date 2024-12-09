package lol.bvlabs.yessir.module.garcom.domain.role;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroRole(
		@NotBlank
		String nome
) {}
