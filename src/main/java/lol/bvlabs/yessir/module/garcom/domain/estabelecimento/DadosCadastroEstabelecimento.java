package lol.bvlabs.yessir.module.garcom.domain.estabelecimento;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroEstabelecimento(
		@NotBlank
		String nome
) {}
