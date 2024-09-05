package lol.bvlabs.yessir.module.garcom.domain.cardapio;

import java.math.BigDecimal;

public record DadosCadastroCardapio(
		Long estabelecimento_id,
		String nome,
		BigDecimal preco
) {}
