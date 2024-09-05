package lol.bvlabs.yessir.module.garcom.domain.cardapio;

import java.math.BigDecimal;

public record DadosAtualizacaoCardapio(
	Long id,
	String nome,
	BigDecimal preco
) {}
