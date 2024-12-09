package lol.bvlabs.yessir.module.garcom.domain.atendente;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroAtendente(
		@NotBlank
		String nome
) {}
