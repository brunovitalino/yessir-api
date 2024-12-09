package lol.bvlabs.yessir.module.garcom.domain.cardapio;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroCardapio(
		@NotNull
		Long estabelecimento_id,
		@NotBlank
		String nome,
		@NotNull
		BigDecimal preco
) {}
