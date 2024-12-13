package lol.bvlabs.yessir.domain.estabelecimento;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroEstabelecimento(
		@NotBlank
		String nome
) {}
