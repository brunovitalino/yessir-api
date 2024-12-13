package lol.bvlabs.yessir.domain.atendente;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroAtendente(
		@NotBlank
		String nome
) {}
